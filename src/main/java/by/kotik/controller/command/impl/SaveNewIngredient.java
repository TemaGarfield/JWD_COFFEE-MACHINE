package by.kotik.controller.command.impl;

import by.kotik.bean.Ingredient;
import by.kotik.controller.command.Command;
import by.kotik.service.IngredientService;
import by.kotik.service.ServiceException;
import by.kotik.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class SaveNewIngredient implements Command {
    private final String COST = "cost";
    private final String TYPE = "type";
    private final String AMOUNT = "amount";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BigDecimal cost = new BigDecimal(request.getParameter(COST));
        String type = request.getParameter(TYPE);
        int amount = Integer.valueOf(request.getParameter(AMOUNT));

        Ingredient ingredient = new Ingredient(cost, type, amount);

        ServiceProvider provider = ServiceProvider.getInstance();
        IngredientService ingredientService = provider.getIngredientService();

        try {
            ingredientService.addIngredient(ingredient);
            response.sendRedirect("Controller?command=gotoadminpage&message=OK");
        } catch (ServiceException e) {
            response.sendRedirect("error.jsp");
        }

    }
}
