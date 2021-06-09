package by.kotik.service;

import by.kotik.bean.Coffee;

import java.util.List;

public interface CoffeeService {
    List<Coffee> all() throws ServiceException;
    boolean addCoffee(Coffee coffee) throws ServiceException;
    boolean deleteCoffee(int id) throws ServiceException;
    Coffee getCoffeeById(int id) throws ServiceException;
    boolean editCoffee(Coffee coffee) throws ServiceException;
}
