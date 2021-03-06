package by.kotik.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.kotik.bean.Admin;
import by.kotik.bean.User;
import by.kotik.dao.DAOException;
import by.kotik.dao.UserDAO;
import by.kotik.dao.connectionpool.ConnectionPollException;
import by.kotik.dao.connectionpool.ConnectionPool;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserDAO implements UserDAO {
    private static final Logger logger = Logger.getLogger(SQLUserDAO.class);

    private final String USER_ID = "iduser";
    private final String USER_USERNAME = "username";
    private final String USER_PASSWORD = "password";
    private final String USER_BALANCE = "balance";
    private final String USER_IS_ADMIN = "is_admin";

    private final String AUTHORIZATION_QUERY = "select * from user where username=?";
    private final String REGISTRATION_QUERY = "insert into user(username, password, balance, is_admin) values(?, ?, ?, ?)";
    private final String TOP_UP_BALANCE_QUERY = "update user set balance = ? where iduser = ?";
    private final String GET_USERNAME_QUERY = "select username from user where username=?";


    @Override
    public User authorization(String login, String password) throws DAOException{
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(AUTHORIZATION_QUERY);
            statement.setString(1, login);
            resultSet = statement.executeQuery();

            int id = 0;
            String username = "";
            String userPassword = "";
            BigDecimal balance = new BigDecimal(0);
            boolean isAdmin = false;

            while (resultSet.next()) {
                id = resultSet.getInt(USER_ID);
                username = resultSet.getString(USER_USERNAME);
                userPassword = resultSet.getString(USER_PASSWORD);
                balance = resultSet.getBigDecimal(USER_BALANCE);
                isAdmin = resultSet.getBoolean(USER_IS_ADMIN);
            }

            if (isAdmin) {
                Admin admin = new Admin(username, userPassword, balance);
                return admin;
            } else {
                User user = new User(id, username, userPassword, balance);
                return user;
            }


        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        }
        finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

    }

    @Override
    public boolean registration(User user) throws DAOException, ConnectionPollException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();

            String hashPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
            user.setPassword(hashPassword);

            PreparedStatement preparedStatement = connection.prepareStatement(REGISTRATION_QUERY);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDouble(3, user.getBalance().doubleValue());
            preparedStatement.setBoolean(4, user.isAdmin());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPollException e) {
            throw new ConnectionPollException(e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public boolean topUpBalance(User user) throws DAOException, ConnectionPollException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TOP_UP_BALANCE_QUERY);

            preparedStatement.setBigDecimal(1, user.getBalance());
            preparedStatement.setInt(2, user.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public boolean isUserInDB(User user) throws DAOException, ConnectionPollException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(GET_USERNAME_QUERY);

            preparedStatement.setString(1, user.getUsername());
            resultSet = preparedStatement.executeQuery();

            if (resultSet == null) {
                return false;
            }

            return true;
        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public boolean isUserInDB(String login) throws DAOException {
        try (
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                Connection connection = connectionPool.takeConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_USERNAME_QUERY)
                ) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return false;
            }

            return true;
        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        }
    }
}
