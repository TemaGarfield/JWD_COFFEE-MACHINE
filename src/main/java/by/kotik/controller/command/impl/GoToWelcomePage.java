package by.kotik.controller.command.impl;

import by.kotik.bean.Coffee;
import by.kotik.bean.Ingredient;
import by.kotik.bean.Order;
import by.kotik.bean.User;
import by.kotik.controller.command.Command;
import by.kotik.service.CoffeeService;
import by.kotik.service.IngredientService;
import by.kotik.service.ServiceException;
import by.kotik.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToWelcomePage implements Command {
    private final String ORDER = "order";
    private final String USER = "loggedUser";
    private final String COFFEE_LIST = "coffeeList";
    private final String INGREDIENT_LIST = "ingredientList";

    private final String WELCOME_PATH = "WEB-INF/jsp/welcome.jsp";
    private final String ERROR_PATH = "error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(WELCOME_PATH);
        HttpSession session = request.getSession();

        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");

        if (session.getAttribute(USER) == null) {
            response.sendRedirect(ERROR_PATH);
            return;
        }

        List<Coffee> coffeeList;
        List<Ingredient> ingredientList;

        if (session.getAttribute(ORDER) == null) {
            Order order = new Order();
            User user = (User) session.getAttribute(USER);
            order.setIdUser(user.getId());
            session.setAttribute(ORDER, order);
        }

        try {
            ServiceProvider provider = ServiceProvider.getInstance();
            CoffeeService coffeeService = provider.getCoffeeService();
            IngredientService ingredientService = provider.getIngredientService();

            coffeeList = coffeeService.all();
            ingredientList = ingredientService.getAllIngredient();
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PATH);
            throw new ServletException(e);
        }

        session.setAttribute(COFFEE_LIST, coffeeList);
        session.setAttribute(INGREDIENT_LIST, ingredientList);


        requestDispatcher.forward(request, response);
    }
}
