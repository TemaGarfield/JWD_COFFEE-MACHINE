package by.kotik.controller.command.impl;

import by.kotik.bean.Coffee;
import by.kotik.controller.command.Command;
import by.kotik.service.CoffeeService;
import by.kotik.service.ServiceException;
import by.kotik.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class SaveNewCoffee implements Command {

    private final String TYPE = "type";
    private final String COST = "cost";
    private final String AMOUNT = "amount";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter(TYPE);
        BigDecimal cost = new BigDecimal(request.getParameter(COST));
        int amount = Integer.valueOf(request.getParameter(AMOUNT));

        Coffee coffee = new Coffee(type, cost, amount);

        ServiceProvider provider = ServiceProvider.getInstance();
        CoffeeService coffeeService = provider.getCoffeeService();

        try {
            coffeeService.addCoffee(coffee);
            response.sendRedirect("Controller?command=gotoadminpage&message=OK");
        } catch (ServiceException e) {
            response.sendRedirect("error.jsp");
        }
    }
}
