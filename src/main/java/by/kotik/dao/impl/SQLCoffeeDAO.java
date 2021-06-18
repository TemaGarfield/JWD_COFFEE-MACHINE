package by.kotik.dao.impl;

import by.kotik.bean.Coffee;
import by.kotik.dao.CoffeeDAO;
import by.kotik.dao.ConnectionProvider;
import by.kotik.dao.DAOException;
import by.kotik.dao.connectionpool.ConnectionPollException;
import by.kotik.dao.connectionpool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLCoffeeDAO implements CoffeeDAO {
    private final String GET_ALL_COFFEE_QUERY = "select * from coffee";
    private final String ADD_COFFEE_QUERY = "insert into coffee(type, cost, amount) values(?, ?, ?)";
    private final String DELETE_COFFEE_QUERY = "delete from coffee where idcoffee = ?";
    private final String GET_COFFEE_BY_ID_QUERY = "select * from coffee where idcoffee = ?";
    private final String EDIT_COFFEE_QUERY = "update coffee set type=?, cost=?, amount=? where idcoffee=?";

    private final String COFFEE_ID = "idcoffee";
    private final String COFFEE_TYPE = "type";
    private final String COFFEE_COST = "cost";
    private final String COFFEE_AMOUNT = "amount";



    public List<Coffee> getAllCoffee() throws DAOException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
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
        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        }
        finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public boolean addCoffee(Coffee coffee) throws DAOException {
        ConnectionPool connectionPool = null;
        Connection connection = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_COFFEE_QUERY);
            preparedStatement.setString(1, coffee.getType());
            preparedStatement.setDouble(2, coffee.getCost().doubleValue());
            preparedStatement.setInt(3, coffee.getAmount());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public boolean deleteCoffee(int id) throws DAOException{
        boolean rowDeleted;
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(DELETE_COFFEE_QUERY);
            preparedStatement.setInt(1, id);

            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }

        return rowDeleted;
    }

    @Override
    public Coffee getCoffeeById(int id) throws DAOException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Coffee coffee = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
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

        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public boolean editCoffee(Coffee coffee) throws DAOException{
        boolean rowEdited;
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(EDIT_COFFEE_QUERY);

            preparedStatement.setString(1, coffee.getType());
            preparedStatement.setBigDecimal(2, coffee.getCost());
            preparedStatement.setInt(3, coffee.getAmount());
            preparedStatement.setInt(4, coffee.getId());

            rowEdited = preparedStatement.executeUpdate() > 0;
            return rowEdited;
        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }
}
