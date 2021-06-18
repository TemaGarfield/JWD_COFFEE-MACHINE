package by.kotik.service.impl;

import by.kotik.bean.Coffee;
import by.kotik.dao.CoffeeDAO;
import by.kotik.dao.DAOException;
import by.kotik.dao.DAOProvider;
import by.kotik.service.CoffeeService;
import by.kotik.service.ServiceException;

import java.util.List;

public class CoffeeServiceImpl implements CoffeeService {
    @Override
    public List<Coffee> all() throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        CoffeeDAO coffeeDAO = provider.getCoffeeDAO();

        List<Coffee> coffeeList;
        try {
            coffeeList = coffeeDAO.getAllCoffee();
            return coffeeList;
        } catch (DAOException e) {
            throw new ServiceException("Coffee service error!", e);
        }

    }

    @Override
    public boolean addCoffee(Coffee coffee) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        CoffeeDAO coffeeDAO = provider.getCoffeeDAO();

        try {
            coffeeDAO.addCoffee(coffee);
            return true;
        } catch (DAOException e) {
            throw new ServiceException("Fail to add coffee", e);
        }
    }

    @Override
    public boolean deleteCoffee(int id) throws ServiceException{
        DAOProvider provider = DAOProvider.getInstance();
        CoffeeDAO coffeeDAO = provider.getCoffeeDAO();

        try {
            coffeeDAO.deleteCoffee(id);
        } catch (DAOException e) {
            throw new ServiceException("Fail to delete coffee", e);
        }

        return true;
    }

    @Override
    public Coffee getCoffeeById(int id) throws ServiceException{
        DAOProvider provider = DAOProvider.getInstance();
        CoffeeDAO coffeeDAO = provider.getCoffeeDAO();

        Coffee coffee;

        try {
            coffee = coffeeDAO.getCoffeeById(id);
            return coffee;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean editCoffee(Coffee coffee) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        CoffeeDAO coffeeDAO = provider.getCoffeeDAO();

        try {
            coffeeDAO.editCoffee(coffee);
            return true;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
