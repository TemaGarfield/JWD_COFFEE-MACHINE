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

public class GoToEditCoffee implements Command {
    private final String ID = "id";
    private final String COFFEE = "coffee";

    private final String EDIT_COFFEE_PATH = "/WEB-INF/jsp/edit-coffee.jsp";
    private final String ERROR_PATH = "error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Integer id  = Integer.parseInt(request.getParameter(ID));
        ServiceProvider provider = ServiceProvider.getInstance();
        CoffeeService service = provider.getCoffeeService();
        try {
            Coffee existingCoffee = service.getCoffeeById(id);
            session.setAttribute(COFFEE, existingCoffee);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PATH);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(EDIT_COFFEE_PATH);
        requestDispatcher.forward(request, response);
    }
}
