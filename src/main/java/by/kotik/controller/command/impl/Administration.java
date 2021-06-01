package by.kotik.controller.command.impl;

import by.kotik.bean.Coffee;
import by.kotik.controller.command.Command;
import by.kotik.service.CoffeeService;
import by.kotik.service.ServiceException;
import by.kotik.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class Administration implements Command {
    private final ServiceProvider provider = ServiceProvider.getInstance();
    private final CoffeeService coffeeService = provider.getCoffeeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Coffee> coffeeList;
        try {
            coffeeList = getAllCoffee();
            if (coffeeList.isEmpty()) {
                System.out.println("empty");
            } else {
                System.out.println("2");
            }
            for (Coffee coffee:coffeeList) {
                System.out.println(coffee.getType());
            }
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        session.setAttribute("coffeeList", coffeeList);
        response.sendRedirect("Controller?command=gotoadminpage");
    }

    public List<Coffee> getAllCoffee() throws ServiceException {
        List<Coffee> coffeeList =  coffeeService.all();
        return coffeeList;
    }
}
