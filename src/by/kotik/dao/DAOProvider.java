package by.kotik.dao;

import by.kotik.dao.impl.SQLUserDAO;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    private DAOProvider(){}

    private final UserDAO userDAO = new SQLUserDAO();

    public static DAOProvider getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
