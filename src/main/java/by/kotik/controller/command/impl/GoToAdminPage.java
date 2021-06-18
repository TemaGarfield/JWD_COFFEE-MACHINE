package by.kotik.controller.command.impl;

import by.kotik.bean.Coffee;
import by.kotik.controller.command.Command;
import by.kotik.service.CoffeeService;
import by.kotik.service.ServiceException;
import by.kotik.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToAdminPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/admin.jsp");
        HttpSession session = request.getSession();
        List<Coffee> coffeeList;
        try {
            ServiceProvider provider = ServiceProvider.getInstance();
            CoffeeService coffeeService = provider.getCoffeeService();

            coffeeList = coffeeService.all();
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        session.setAttribute("coffeeList", coffeeList);
        requestDispatcher.forward(request, response);
    }
}