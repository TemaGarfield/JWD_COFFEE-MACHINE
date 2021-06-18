package by.kotik.service;

import by.kotik.service.impl.CoffeeServiceImpl;
import by.kotik.service.impl.IngredientServiceImpl;
import by.kotik.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private ServiceProvider(){}

    private final UserService userService = new UserServiceImpl();
    private final CoffeeService coffeeService = new CoffeeServiceImpl();
    private final IngredientService ingredientService = new IngredientServiceImpl();

    public static ServiceProvider getInstance(){
        return  instance;
    }

    public UserService getUserService() {
        return userService;
    }
    public CoffeeService getCoffeeService() { return coffeeService; }
    public IngredientService getIngredientService() { return ingredientService; }
}
