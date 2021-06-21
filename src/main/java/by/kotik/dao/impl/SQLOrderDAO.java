package by.kotik.dao.impl;

import by.kotik.bean.Coffee;
import by.kotik.bean.Ingredient;
import by.kotik.bean.Order;
import by.kotik.dao.*;
import by.kotik.dao.connectionpool.ConnectionPollException;
import by.kotik.dao.connectionpool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLOrderDAO implements OrderDAO {
    private final String ADD_TO_ORDER_QUERY = "insert into `order`(iduser, cost) values(?, ?)";
    private final String SELECT_LAST_ORDER_ID = "select idorder from `order` order by idorder desc limit 1";
    private final String ADD_TO_USER_ORDERS_QUERY = "insert into user_orders(iduser, idorder) values(?, ?)";
    private final String ADD_TO_ORDER_LIST_QUERY = "insert into order_list(idorder, idproduct, producttype) values(?, ?, ?)";

    private final String GET_ORDER_IDS_QUERY = "select idorder from user_orders where iduser=?";
    private final String GET_PRODUCTS_QUERY = "select idproduct, producttype from order_list where idorder=?";
    private final String GET_COST_QUERY = "select cost from `order` where idorder=?";

    private final String COFFEE_STRING = Coffee.class.toString();
    private final String INGREDIENT_STRING = Ingredient.class.toString();
    @Override
    public boolean addOrder(Order order) throws DAOException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(ADD_TO_ORDER_QUERY);

            preparedStatement.setInt(1, order.getIdUser());
            preparedStatement.setDouble(2, order.getCost().doubleValue());

            preparedStatement.executeUpdate();

            int id = -1;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_LAST_ORDER_ID);

            while (resultSet.next()) {
                id = resultSet.getInt("idorder");
            }

            order.setId(id);


            preparedStatement = connection.prepareStatement(ADD_TO_USER_ORDERS_QUERY);
            preparedStatement.setInt(1, order.getIdUser());
            preparedStatement.setInt(2, order.getId());

            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(ADD_TO_ORDER_LIST_QUERY);

            for (Coffee coffee : order.getCoffeeList()) {
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, coffee.getId());
                preparedStatement.setString(3, coffee.getClass().toString());

                preparedStatement.executeUpdate();
            }

            for (Ingredient ingredient : order.getIngredientList()) {
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, ingredient.getId());
                preparedStatement.setString(3, ingredient.getClass().toString());

                preparedStatement.executeUpdate();
            }

            return true;
        } catch (ConnectionPollException | SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);

            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public List<Order> getAllOrders(int idUser) throws DAOException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        DAOProvider provider = DAOProvider.getInstance();
        CoffeeDAO coffeeDAO = provider.getCoffeeDAO();
        IngredientDAO ingredientDAO = provider.getIngredientDAO();

        List<Integer> ordersIdList = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(GET_ORDER_IDS_QUERY);
            preparedStatement.setInt(1, idUser);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ordersIdList.add(resultSet.getInt("idorder"));
            }

            for (Integer integer : ordersIdList) {
                preparedStatement = connection.prepareStatement(GET_PRODUCTS_QUERY);
                preparedStatement.setInt(1, integer);
                resultSet = preparedStatement.executeQuery();

                Order order = new Order();

                while (resultSet.next()) {
                    if (resultSet.getString("producttype").equals(COFFEE_STRING)) {
                        Coffee coffee = coffeeDAO.getCoffeeById(resultSet.getInt("idproduct"));
                        order.addCoffee(coffee);

                    } else if (resultSet.getString("producttype").equals(INGREDIENT_STRING)) {
                        Ingredient ingredient = ingredientDAO.getIngredientById(resultSet.getInt("idProduct"));
                        order.addIngredient(ingredient);
                    }
                }

                preparedStatement = connection.prepareStatement(GET_COST_QUERY);
                preparedStatement.setInt(1, integer);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    order.setCost(resultSet.getBigDecimal("cost"));
                }

                orders.add(order);
            }

        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }

        return orders;
    }

}
