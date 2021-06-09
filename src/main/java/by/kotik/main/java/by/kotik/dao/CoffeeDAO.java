package by.kotik.dao;

import by.kotik.bean.Coffee;

import java.util.List;

public interface CoffeeDAO {
    List<Coffee> getAllCoffee() throws DAOException;
    boolean addCoffee(Coffee coffee) throws DAOException;
    boolean deleteCoffee(int id) throws DAOException;
    Coffee getCoffeeById(int id) throws DAOException;
    boolean editCoffee(Coffee coffee) throws DAOException;
}
