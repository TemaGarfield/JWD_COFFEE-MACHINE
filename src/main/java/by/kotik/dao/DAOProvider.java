package by.kotik.dao;

import by.kotik.dao.impl.SQLCoffeeDAO;
import by.kotik.dao.impl.SQLIngredientDAO;
import by.kotik.dao.impl.SQLUserDAO;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    private DAOProvider(){}

    private final UserDAO userDAO = new SQLUserDAO();
    private final CoffeeDAO coffeeDAO = new SQLCoffeeDAO();
    private final IngredientDAO ingredientDAO = new SQLIngredientDAO();

    public static DAOProvider getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
    public CoffeeDAO getCoffeeDAO() { return coffeeDAO; }
    public IngredientDAO getIngredientDAO() { return ingredientDAO; }
}
