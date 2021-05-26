package by.kotik.service.impl;

import by.kotik.bean.User;
import by.kotik.dao.DAOException;
import by.kotik.dao.DAOProvider;
import by.kotik.dao.UserDAO;
import by.kotik.service.ServiceException;
import by.kotik.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User authorization(String login, String password) throws ServiceException {
        if (login == null || "".equals(login) || password == null || "".equals("password")) {
            throw new ServiceException("wrong input");
        }

        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();

        User user = null;
        try {
            user = userDAO.authorization(login, password);
        } catch (DAOException e) {
            System.out.println("Authorization service");
            throw new ServiceException("error message", e);
        }

        return user;
    }

    @Override
    public boolean registration(User user) throws ServiceException{
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();

        try {
            userDAO.registration(user);
            return true;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
