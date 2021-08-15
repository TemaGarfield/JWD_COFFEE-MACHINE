package by.kotik.service;

import by.kotik.bean.User;

import java.math.BigDecimal;
import java.util.List;

public interface AdminService {
    List<User> getAllUsers() throws ServiceException;
    boolean deleteUser(int id) throws ServiceException;
    String updateUser(int id, String username, String password, BigDecimal balance) throws ServiceException;
    User getUserById(int id) throws ServiceException;
    String addUser(String username, String password, String balance) throws ServiceException;
}
