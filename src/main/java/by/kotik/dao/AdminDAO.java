package by.kotik.dao;

import by.kotik.bean.User;

import java.util.List;

public interface AdminDAO {
    boolean deleteUser(int id) throws DAOException;
    boolean updateUser(User user) throws DAOException;
    List<User> getAllUsers() throws DAOException;
    User getUserById(int id) throws DAOException;
    boolean addUser(User user) throws DAOException;
}
