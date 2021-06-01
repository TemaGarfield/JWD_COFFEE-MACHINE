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
    private final String PATH = "jdbc:mysql://localhost:3306/coffee-machine";
    private final String USERNAME = "root";
    private final String PASSWORD = "KotikPa55word";
    static {
        MYSQLDriverLoader.getInstance();
    }


    public List<Coffee> all() throws DAOException {
        Connection connection = null;
        Statement statement;
        ResultSet resultSet;

        try {
            String query = "select * from coffee";
            connection = DriverManager.getConnection(PATH, USERNAME, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Coffee> coffeeList = new ArrayList<>();
            String type;
            BigDecimal cost;
            int amount;

            while (resultSet.next()) {
                type = resultSet.getString("type");
                cost = resultSet.getBigDecimal("cost");
                amount = resultSet.getInt("amount");
                coffeeList.add(new Coffee(type, cost, amount));
            }

            return coffeeList;
        } catch (SQLException e) {
            System.out.println("error dao coffee!");
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
