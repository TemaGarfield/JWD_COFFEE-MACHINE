package by.kotik.dao;

import by.kotik.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDatabase {
    Connection con;

    public UserDatabase(Connection con) {
        this.con = con;
    }

    public boolean saveUser(User user) {
        boolean set = false;

        try {
            String query = "insert into user(username, password, balance) values(?, ?, ?)";

            PreparedStatement preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDouble(3, user.getBalance().doubleValue());

            preparedStatement.executeUpdate();
            set = true;
        } catch(Exception e) {

        }
        return set;
    }
}
