package by.kotik.dao;

import by.kotik.bean.User;
import by.kotik.dao.connectionpool.ConnectionPollException;

import java.util.List;

public interface UserDAO {
    User authorization(String login, String password) throws DAOException, ConnectionPollException;
    boolean registration(User user) throws DAOException, ConnectionPollException;
    boolean topUpBalance(User user) throws DAOException, ConnectionPollException;
}
