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
import java.io.IOException;

public class GoToEditIngredient implements Command {
    private final String INGREDIENT_ID = "id";
    private final String PATH = "/WEB-INF/jsp/edit-ingredient.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(INGREDIENT_ID));

        ServiceProvider provider = ServiceProvider.getInstance();
        IngredientService service = provider.getIngredientService();

        try {
            Ingredient existingIngredient = service.getIngredientById(id);
            request.setAttribute("ingredient", existingIngredient);
        } catch (ServiceException e) {
            response.sendRedirect("error.jsp");
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
        requestDispatcher.forward(request, response);
    }
}
