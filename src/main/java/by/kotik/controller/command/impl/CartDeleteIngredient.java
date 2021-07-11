package by.kotik.controller.command.impl;

import by.kotik.bean.Order;
import by.kotik.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class CartDeleteIngredient implements Command {
    private final String PATH = "WEB-INF/jsp/cart.jsp";

    private final String ORDER = "order";
    private final String INDEX_INGREDIENT = "indexIngredient";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
        HttpSession session = request.getSession();

        int index = Integer.parseInt(request.getParameter(INDEX_INGREDIENT));
        Order order = (Order) session.getAttribute(ORDER);
        BigDecimal nowCost = order.getCost();
        BigDecimal ingredientCost = order.getIngredientList().get(index).getCost();
        BigDecimal newCost = nowCost.subtract(ingredientCost);

        order.setCost(newCost);
        order.getIngredientList().remove(index);

        requestDispatcher.forward(request, response);
    }
}
