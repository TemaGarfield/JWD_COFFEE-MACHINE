package by.kotik.service;

import by.kotik.bean.User;

public interface UserService {
    public User authorization(String login, String password) throws ServiceException;
    public boolean registration(User user) throws ServiceException;

}
