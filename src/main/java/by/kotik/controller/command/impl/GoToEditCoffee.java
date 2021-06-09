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
import java.io.IOException;

public class GoToEditCoffee implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ServiceProvider provider = ServiceProvider.getInstance();
        CoffeeService service = provider.getCoffeeService();
        try {
            Coffee existingCoffee = service.getCoffeeById(id);
            request.setAttribute("coffee", existingCoffee);
        } catch (ServiceException e) {
            response.sendRedirect("error.jsp");
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/edit-coffee.jsp");
        requestDispatcher.forward(request, response);
    }
}
