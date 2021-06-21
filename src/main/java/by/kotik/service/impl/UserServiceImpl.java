package by.kotik.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.kotik.bean.User;
import by.kotik.dao.DAOException;
import by.kotik.dao.DAOProvider;
import by.kotik.dao.UserDAO;
import by.kotik.dao.connectionpool.ConnectionPollException;
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

        User user;
        try {
            user = userDAO.authorization(login, password);
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

            if (result.verified) {
                return user;
            } else {
                return null;
            }
        } catch (DAOException e) {
            throw new ServiceException("Authorization exception", e);
        } catch (ConnectionPollException e) {
            throw new ServiceException("Authorization exception", e);
        }
    }

    @Override
    public boolean registration(User user) throws ServiceException{
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();

        try {
            userDAO.registration(user);
            return true;
        } catch (DAOException | ConnectionPollException e) {
            throw new ServiceException("Registration exception", e);
        }

    }

    @Override
    public boolean topUpBalance(User user) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();

        try {
            userDAO.topUpBalance(user);
            return true;
        } catch (DAOException | ConnectionPollException e) {
            throw new ServiceException(e);
        }
    }
}
