package by.kotik.service.impl;

import by.kotik.bean.User;
import by.kotik.dao.AdminDAO;
import by.kotik.dao.DAOException;
import by.kotik.dao.DAOProvider;
import by.kotik.dao.impl.SQLAdminDAO;
import by.kotik.service.AdminService;
import by.kotik.service.ServiceException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private final String ERROR_USERNAME = "locale.error.edit_users.error_username";
    private final String ERROR_PASSWORD = "locale.error.edit_users.error_password";
    private final String ERROR_BALANCE = "locale.error.edit_users.error_balance";

    private DAOProvider provider = DAOProvider.getInstance();
    private AdminDAO adminDAO = provider.getAdminDAO();

    @Override
    public List<User> getAllUsers() throws ServiceException{
        List<User> users;

        try {
            users = adminDAO.getAllUsers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return users;
    }

    @Override
    public boolean deleteUser(int id) throws ServiceException {
        try {
            return adminDAO.deleteUser(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String updateUser(int id, String username, String password, BigDecimal balance) throws ServiceException {
        String message = "";

        if (username.isBlank()) {
            message = ERROR_USERNAME;
            return message;
        }

        if (balance.compareTo(new BigDecimal(0.0)) < 0) {
            message = ERROR_BALANCE;
            return message;
        }

        if (!password.isEmpty()) {
            if (password.trim().isEmpty()) {
                message = ERROR_PASSWORD;
                return message;
            }

        }

        User user = new User(id, username, password, balance);

        try {
            adminDAO.updateUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return message;
    }

    @Override
    public User getUserById(int id) throws ServiceException {
        try {
            return adminDAO.getUserById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String addUser(String username, String password, String balance) throws ServiceException{
        String message = "";

        if (username.isEmpty()) {
            message = ERROR_USERNAME;
            return message;
        }

        if (password.isEmpty() || password.trim().isEmpty()) {
            message = ERROR_PASSWORD;
            return message;
        }

        BigDecimal convertedBalance;
        if (balance.isEmpty()) {
            convertedBalance = new BigDecimal(0.0);
        } else {
            convertedBalance = new BigDecimal(balance);
        }

        if (convertedBalance.compareTo(new BigDecimal(0.0)) < 0) {
            message = ERROR_BALANCE;
            return message;
        }

        User user = new User(username, password, convertedBalance);

        try {
            adminDAO.addUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return message;
    }
}
