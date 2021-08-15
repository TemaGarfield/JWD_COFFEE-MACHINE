package by.kotik.controller.command.impl;

import by.kotik.bean.Ingredient;
import by.kotik.controller.command.Command;
import by.kotik.service.IngredientService;
import by.kotik.service.ServiceException;
import by.kotik.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToEditIngredient implements Command {
    private final String INGREDIENT_ID = "id";
    private final String INGREDIENT = "ingredient";
    private final String USER = "loggedUser";

    private final String EDIT_INGREDIENT_PATH = "/WEB-INF/jsp/edit-ingredient.jsp";
    private final String ERROR_PATH = "error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");

        if (session.getAttribute(USER) == null) {
            response.sendRedirect(ERROR_PATH);
            return;
        }

        int id = Integer.parseInt(request.getParameter(INGREDIENT_ID));

        ServiceProvider provider = ServiceProvider.getInstance();
        IngredientService service = provider.getIngredientService();

        try {
            Ingredient existingIngredient = service.getIngredientById(id);
            session.setAttribute(INGREDIENT, existingIngredient);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PATH);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(EDIT_INGREDIENT_PATH);
        requestDispatcher.forward(request, response);
    }
}
