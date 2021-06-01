package by.kotik.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.kotik.bean.Admin;
import by.kotik.bean.User;
import by.kotik.dao.DAOException;
import by.kotik.dao.UserDAO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class SQLUserDAO implements UserDAO {
    private final String PATH = "jdbc:mysql://localhost:3306/coffee-machine";
    private final String USERNAME = "root";
    private final String PASSWORD = "KotikPa55word";
    static {
        MYSQLDriverLoader.getInstance();
    }

    @Override
    public User authorization(String login, String password) throws DAOException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "select * from user where username='" + login + "'";
            connection = DriverManager.getConnection(PATH, USERNAME, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            String username = "";
            String userPassword = "";
            BigDecimal balance = new BigDecimal(0);
            boolean isAdmin = false;

            while (resultSet.next()) {
                username = resultSet.getString("username");
                userPassword = resultSet.getString("password");
                balance = resultSet.getBigDecimal("balance");
                isAdmin = resultSet.getBoolean("is_admin");
            }

            resultSet.close();



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
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }

    }

    @Override
    public boolean registration(User user) throws DAOException {
        Connection connection = null;
        try {
            String query = "insert into user(username, password, balance, is_admin) values(?, ?, ?, ?)";
            connection = DriverManager.getConnection(PATH, USERNAME, PASSWORD);

            String hashPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
            user.setPassword(hashPassword);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDouble(3, user.getBalance().doubleValue());
            preparedStatement.setBoolean(4, user.isAdmin());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("DAO!");
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
