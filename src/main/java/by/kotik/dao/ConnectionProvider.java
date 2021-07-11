package by.kotik.dao;

import java.sql.*;

public class ConnectionProvider {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coffee-machine", "root", "KotikPa55word");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }


}
