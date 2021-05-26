package by.kotik.dao.impl;

import by.kotik.bean.User;
import by.kotik.dao.DAOException;
import by.kotik.dao.UserDAO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class SQLUserDAO implements UserDAO {
    private final String PATH = "jdbc:mysql://localhost:3306/coffee-machine";
    private final String USERNAME = "root";
    private final String PASSWORD = "123";
    static {
        MYSQLDriverLoader.getInstance();
    }

    @Override
    public User authorization(String login, String password) throws DAOException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "select * from user where username='" + login + "' and password='" + password+"\';";
            connection = DriverManager.getConnection(PATH, USERNAME, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            String username = "";
            String userPassword = "";
            BigDecimal balance = new BigDecimal(0);
            while (resultSet.next()) {
                username = resultSet.getString("username");
                userPassword = resultSet.getString("password");
                balance = resultSet.getBigDecimal("balance");
            }

            resultSet.close();

            User user = new User(username, userPassword, balance);

            return user;


        } catch (SQLException e) {
            System.out.println("DAO ERROR!");
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
    public boolean registration(User user) throws DAOException {
        Connection connection = null;

        try {
            String query = "insert into user(username, password, balance) values(?, ?, ?)";
            connection = DriverManager.getConnection(PATH, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDouble(3, user.getBalance().doubleValue());

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
