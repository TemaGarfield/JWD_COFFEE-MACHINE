package by.kotik.service;

import by.kotik.bean.User;
import by.kotik.dao.connectionpool.ConnectionPollException;

public interface UserService {
    User authorization(String login, String password) throws ServiceException;
    boolean registration(User user) throws ServiceException;
    boolean topUpBalance(User user) throws ServiceException;
    boolean isUserInDB(User user) throws ServiceException;

}
