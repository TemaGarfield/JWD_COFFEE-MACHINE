package by.kotik.service;

import by.kotik.service.impl.*;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private ServiceProvider(){}

    private final UserService userService = new UserServiceImpl();
    private final CoffeeService coffeeService = new CoffeeServiceImpl();
    private final IngredientService ingredientService = new IngredientServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final AdminService adminService = new AdminServiceImpl();

    public static ServiceProvider getInstance(){
        return  instance;
    }

    public UserService getUserService() {
        return userService;
    }
    public CoffeeService getCoffeeService() { return coffeeService; }
    public IngredientService getIngredientService() { return ingredientService; }
    public OrderService getOrderService() { return orderService; }
    public AdminService getAdminService() { return adminService; }
}
