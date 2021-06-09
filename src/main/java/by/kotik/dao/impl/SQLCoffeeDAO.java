package by.kotik.dao.impl;

import by.kotik.bean.Coffee;
import by.kotik.dao.CoffeeDAO;
import by.kotik.dao.ConnectionProvider;
import by.kotik.dao.DAOException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLCoffeeDAO implements CoffeeDAO {
    private final String GET_ALL_COFFEE_QUERY = "select * from coffee";
    private final String ADD_COFFEE_QUERY = "insert into coffee(type, cost, amount) values(?, ?, ?)";
    private final String DELETE_COFFEE_QUERY = "delete from coffee where idcoffee = ?";
    private final String GET_COFFEE_BY_ID_QUERY = "select * from coffee where idcoffee = ?";
    private final String EDIT_COFFEE_QUERY = "update coffee set type=?, cost=?, amount=? where id=?";

    private final String COFFEE_ID = "idcoffee";
    private final String COFFEE_TYPE = "type";
    private final String COFFEE_COST = "cost";
    private final String COFFEE_AMOUNT = "amount";

    static {
        MYSQLDriverLoader.getInstance();
    }


    public List<Coffee> getAllCoffee() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_COFFEE_QUERY);

            List<Coffee> coffeeList = new ArrayList<>();
            int id;
            String type;
            BigDecimal cost;
            int amount;

            while (resultSet.next()) {
                id = resultSet.getInt(COFFEE_ID);
                type = resultSet.getString(COFFEE_TYPE);
                cost = resultSet.getBigDecimal(COFFEE_COST);
                amount = resultSet.getInt(COFFEE_AMOUNT);
                coffeeList.add(new Coffee(id, type, cost, amount));
            }

            return coffeeList;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public boolean addCoffee(Coffee coffee) throws DAOException {
        Connection connection = null;

        try {
            connection = ConnectionProvider.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_COFFEE_QUERY);
            preparedStatement.setString(1, coffee.getType());
            preparedStatement.setDouble(2, coffee.getCost().doubleValue());
            preparedStatement.setInt(3, coffee.getAmount());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public boolean deleteCoffee(int id) throws DAOException{
        boolean rowDeleted;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionProvider.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_COFFEE_QUERY);
            preparedStatement.setInt(1, id);

            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return rowDeleted;
    }

    @Override
    public Coffee getCoffeeById(int id) throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Coffee coffee = null;

        try {
            connection = ConnectionProvider.getConnection();
            preparedStatement = connection.prepareStatement(GET_COFFEE_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            int idCoffee;
            String type;
            BigDecimal cost;
            int amount;

            while (resultSet.next()) {
                idCoffee = resultSet.getInt(COFFEE_ID);
                type = resultSet.getString(COFFEE_TYPE);
                cost = resultSet.getBigDecimal(COFFEE_COST);
                amount = resultSet.getInt(COFFEE_AMOUNT);
                coffee = new Coffee(idCoffee, type, cost, amount);
            }

            return coffee;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                connection.close();
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public boolean editCoffee(Coffee coffee) throws DAOException{
        boolean rowEdited;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionProvider.getConnection();
            preparedStatement = connection.prepareStatement(EDIT_COFFEE_QUERY);

            preparedStatement.setString(1, coffee.getType());
            preparedStatement.setBigDecimal(2, coffee.getCost());
            preparedStatement.setInt(3, coffee.getAmount());

            rowEdited = preparedStatement.executeUpdate() > 0;

            return rowEdited;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
