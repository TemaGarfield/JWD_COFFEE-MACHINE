package by.kotik.service;

import by.kotik.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private ServiceProvider(){}

    private final UserService userService = new UserServiceImpl();

    public static ServiceProvider getInstance(){
        return  instance;
    }

    public UserService getUserService() {
        return userService;
    }
}
