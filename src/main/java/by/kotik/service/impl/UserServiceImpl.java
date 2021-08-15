package by.kotik.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.kotik.bean.User;
import by.kotik.dao.DAOException;
import by.kotik.dao.DAOProvider;
import by.kotik.dao.UserDAO;
import by.kotik.dao.connectionpool.ConnectionPollException;
import by.kotik.service.ServiceException;
import by.kotik.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final String EMPTY_LOGIN = "locale.error.login.empty_login";
    private final String EMPTY_PASSWORD = "locale.error.login.empty_password";
    private final String ERROR_LOGIN = "locale.error.login.error_login";
    private final String ERROR_PASSWORD = "locale.error.login.error_password";

    @Override
    public Optional<User> authorization(String login, String password) throws ServiceException {

        Optional<User> user;

        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();

        try {
            user = Optional.ofNullable(userDAO.authorization(login, password));
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.get().getPassword());

            if (!result.verified) {
                user = Optional.empty();
                return user;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return user;
//        if (login == null || "".equals(login) || password == null || "".equals("password")) {
//            return null;
//        }
//
//        DAOProvider provider = DAOProvider.getInstance();
//        UserDAO userDAO = provider.getUserDAO();
//
//        User user;
//        try {
//            user = userDAO.authorization(login, password);
//            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
//
//            if (!result.verified) {
//                user.setUsername(login);
//                return null;
//            }
//
//            return user;
//        } catch (DAOException e) {
//            throw new ServiceException("Authorization exception", e);
//        } catch (ConnectionPollException e) {
//            throw new ServiceException("Authorization exception", e);
//        }
    }

    @Override
    public boolean registration(User user) throws ServiceException{
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();

        try {

            return userDAO.registration(user);
        } catch (DAOException | ConnectionPollException e) {
            throw new ServiceException("Registration exception", e);
        }

    }

    @Override
    public boolean topUpBalance(User user) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();

        try {

            return userDAO.topUpBalance(user);
        } catch (DAOException | ConnectionPollException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isUserInDB(String login) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();

        try {
            return userDAO.isUserInDB(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String verifier(String login, String password) throws ServiceException{
        String message = "";

        if (login.isBlank()) {
            message = EMPTY_LOGIN;
            return message;
        }

        if (password.isBlank()) {
            message = EMPTY_PASSWORD;
            return message;
        }

        try {
            if (!isUserInDB(login)) {
                message = ERROR_LOGIN;
                return message;
            }

            if (!authorization(login, password).isPresent()) {
                message = ERROR_PASSWORD;
                return message;
            }
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }

        return message;
    }
}
