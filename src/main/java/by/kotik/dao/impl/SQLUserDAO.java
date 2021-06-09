package by.kotik.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.kotik.bean.Admin;
import by.kotik.bean.User;
import by.kotik.dao.ConnectionProvider;
import by.kotik.dao.DAOException;
import by.kotik.dao.UserDAO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class SQLUserDAO implements UserDAO {
    private final String USER_USERNAME = "username";
    private final String USER_PASSWORD = "password";
    private final String USER_BALANCE = "balance";
    private final String USER_IS_ADMIN = "is_admin";

    private final String AUTHORIZATION_QUERY = "select * from user where username=?";
    private final String REGISTRATION_QUERY = "insert into user(username, password, balance, is_admin) values(?, ?, ?, ?)";

    static {
        MYSQLDriverLoader.getInstance();
    }

    @Override
    public User authorization(String login, String password) throws DAOException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(AUTHORIZATION_QUERY);
            statement.setString(1, login);
            resultSet = statement.executeQuery();

            String username = "";
            String userPassword = "";
            BigDecimal balance = new BigDecimal(0);
            boolean isAdmin = false;

            while (resultSet.next()) {
                username = resultSet.getString(USER_USERNAME);
                userPassword = resultSet.getString(USER_PASSWORD);
                balance = resultSet.getBigDecimal(USER_BALANCE);
                isAdmin = resultSet.getBoolean(USER_IS_ADMIN);
            }

            if (isAdmin) {
                Admin admin = new Admin(username, userPassword, balance);
                return admin;
            } else {
                User user = new User(username, userPassword, balance);
                return user;
            }


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
    public boolean registration(User user) throws DAOException {
        Connection connection = null;
        try {
            connection = ConnectionProvider.getConnection();

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
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
