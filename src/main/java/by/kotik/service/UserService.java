package by.kotik.service;

import by.kotik.bean.User;

import java.util.Optional;

public interface UserService {
    Optional<User> authorization(String login, String password) throws ServiceException;
    boolean registration(User user) throws ServiceException;
    boolean topUpBalance(User user) throws ServiceException;
//    boolean isUserInDB(User user) throws ServiceException;
    String verifier(String login, String password) throws ServiceException;
    boolean isUserInDB(String login) throws ServiceException;
}
