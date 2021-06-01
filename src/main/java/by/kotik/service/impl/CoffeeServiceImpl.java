package by.kotik.service.impl;

import by.kotik.bean.Coffee;
import by.kotik.dao.CoffeeDAO;
import by.kotik.dao.DAOException;
import by.kotik.dao.DAOProvider;
import by.kotik.service.CoffeeService;
import by.kotik.service.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class CoffeeServiceImpl implements CoffeeService {
    @Override
    public List<Coffee> all() throws ServiceException {
        DAOProvider provider =DAOProvider.getInstance();
        CoffeeDAO coffeeDAO = provider.getCoffeeDAO();

        List<Coffee> coffeeList;
        try {
            coffeeList = coffeeDAO.all();
            return coffeeList;
        } catch (DAOException e) {
            throw new ServiceException("Coffee service error!", e);
        }

    }
}
