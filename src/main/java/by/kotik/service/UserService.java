package by.kotik.service;

import by.kotik.bean.User;
import by.kotik.dao.connectionpool.ConnectionPollException;

public interface UserService {
    public User authorization(String login, String password) throws ServiceException;
    public boolean registration(User user) throws ServiceException;

}
