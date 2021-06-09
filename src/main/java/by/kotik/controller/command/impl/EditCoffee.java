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

public class EditCoffee implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        BigDecimal cost = new BigDecimal(request.getParameter("cost"));
        int amount = Integer.valueOf(request.getParameter("amount"));

        Coffee coffee = new Coffee(type, cost, amount);

        ServiceProvider provider = ServiceProvider.getInstance();
        CoffeeService service = provider.getCoffeeService();

        try {
            service.editCoffee(coffee);
            response.sendRedirect("Controller?command=gotoadminpage&message=ok");
        } catch (ServiceException e) {
            response.sendRedirect("error.jsp");
        }


    }
}
