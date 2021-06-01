package by.kotik.dao;

import by.kotik.bean.User;

import java.util.List;

public interface UserDAO {
    User authorization(String login, String password) throws DAOException;
    boolean registration(User user) throws DAOException;
}
