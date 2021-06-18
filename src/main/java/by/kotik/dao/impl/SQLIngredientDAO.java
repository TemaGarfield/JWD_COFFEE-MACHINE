package by.kotik.dao.impl;

import by.kotik.bean.Ingredient;
import by.kotik.dao.DAOException;
import by.kotik.dao.IngredientDAO;
import by.kotik.dao.connectionpool.ConnectionPollException;
import by.kotik.dao.connectionpool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLIngredientDAO implements IngredientDAO {
    private final String GET_ALL_INGREDIENT_QUERY = "select * from ingredient";
    private final String ADD_INGREDIENT_QUERY = "insert into ingredient(cost, type, amount) values (?, ?, ?)";
    private final String DELETE_INGREDIENT_QUERY = "delete from ingredient where idingredient = ?";
    private final String GET_INGREDIENT_BY_ID = "select * from ingredient where idingredient = ?";
    private final String EDIT_INGREDIENT_QUERY = "update ingredient set cost = ?, type = ?, amount = ? where idingredient = ?";

    private final String ID_INGREDIENT = "idingredient";
    private final String INGREDIENT_COST = "cost";
    private final String INGREDIENT_TYPE = "type";
    private final String INGREDIENT_AMOUNT = "amount";

    @Override
    public List<Ingredient> getAllIngredients() throws DAOException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_INGREDIENT_QUERY);

            List<Ingredient> ingredientList = new ArrayList<>();
            int idIngredient;
            BigDecimal cost;
            String type;
            int amount;

            while (resultSet.next()) {
                idIngredient = resultSet.getInt(ID_INGREDIENT);
                cost = resultSet.getBigDecimal(INGREDIENT_COST);
                type = resultSet.getString(INGREDIENT_TYPE);
                amount = resultSet.getInt(INGREDIENT_AMOUNT);

                ingredientList.add(new Ingredient(idIngredient, cost, type, amount));
            }

            return ingredientList;
        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public boolean addIngredient(Ingredient ingredient) throws DAOException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(ADD_INGREDIENT_QUERY);

            preparedStatement.setDouble(1, ingredient.getCost().doubleValue());
            preparedStatement.setString(2, ingredient.getType());
            preparedStatement.setInt(3, ingredient.getAmount());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public boolean deleteIngredient(int id) throws DAOException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(DELETE_INGREDIENT_QUERY);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public Ingredient getIngredientById(int id) throws DAOException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Ingredient ingredient = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(GET_INGREDIENT_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            int idIngredient;
            BigDecimal cost;
            String type;
            int amount;

            while (resultSet.next()) {
                idIngredient = resultSet.getInt(ID_INGREDIENT);
                cost = resultSet.getBigDecimal(INGREDIENT_COST);
                type = resultSet.getString(INGREDIENT_TYPE);
                amount = resultSet.getInt(INGREDIENT_AMOUNT);

                ingredient = new Ingredient(idIngredient, cost, type, amount);
            }

            return ingredient;
        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public boolean editIngredient(Ingredient ingredient) throws DAOException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(EDIT_INGREDIENT_QUERY);

            preparedStatement.setBigDecimal(1, ingredient.getCost());
            preparedStatement.setString(2, ingredient.getType());
            preparedStatement.setInt(3, ingredient.getAmount());
            preparedStatement.setInt(4, ingredient.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }
}
