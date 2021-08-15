package by.kotik.dao;

import by.kotik.bean.User;
import by.kotik.dao.connectionpool.ConnectionPollException;

public interface UserDAO {
    User authorization(String login, String password) throws DAOException;
    boolean registration(User user) throws DAOException, ConnectionPollException;
    boolean topUpBalance(User user) throws DAOException, ConnectionPollException;
    boolean isUserInDB(User user) throws DAOException, ConnectionPollException;
    boolean isUserInDB(String login) throws DAOException;
}
