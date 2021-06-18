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

public class EditIngredient implements Command {
    private final String INGREDIENT_ID = "id";
    private final String INGREDIENT_COST = "cost";
    private final String INGREDIENT_TYPE = "type";
    private final String INGREDIENT_AMOUNT = "amount";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(INGREDIENT_ID));
        BigDecimal cost = new BigDecimal(request.getParameter(INGREDIENT_COST));
        String type = request.getParameter(INGREDIENT_TYPE);
        int amount = Integer.parseInt(request.getParameter(INGREDIENT_AMOUNT));

        Ingredient ingredient = new Ingredient(id, cost, type, amount);

        ServiceProvider provider = ServiceProvider.getInstance();
        IngredientService service = provider.getIngredientService();

        try {
            service.editIngredient(ingredient);
            response.sendRedirect("Controller?command=gotoadminpage&message=ok");
        } catch (ServiceException e) {
            response.sendRedirect("error.jsp");
        }
    }
}
