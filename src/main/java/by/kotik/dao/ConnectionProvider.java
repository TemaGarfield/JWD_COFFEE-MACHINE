package by.kotik.dao;

import java.sql.*;

public class ConnectionProvider {
    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coffee-machine", "root", "KotikPa55word");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }


}
