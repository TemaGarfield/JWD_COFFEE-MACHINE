package by.kotik.dao;

import by.kotik.dao.impl.*;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    private DAOProvider(){}

    private final UserDAO userDAO = new SQLUserDAO();
    private final CoffeeDAO coffeeDAO = new SQLCoffeeDAO();
    private final IngredientDAO ingredientDAO = new SQLIngredientDAO();
    private final OrderDAO orderDAO = new SQLOrderDAO();
    private final AdminDAO adminDAO = new SQLAdminDAO();

    public static DAOProvider getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
    public CoffeeDAO getCoffeeDAO() { return coffeeDAO; }
    public IngredientDAO getIngredientDAO() { return ingredientDAO; }
    public OrderDAO getOrderDAO() { return orderDAO; }
    public AdminDAO getAdminDAO() { return adminDAO; }

}
