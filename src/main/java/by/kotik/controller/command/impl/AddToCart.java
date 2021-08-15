package by.kotik.controller.command.impl;

import by.kotik.bean.Coffee;
import by.kotik.bean.Ingredient;
import by.kotik.bean.Order;
import by.kotik.controller.command.Command;
import by.kotik.service.CoffeeService;
import by.kotik.service.IngredientService;
import by.kotik.service.ServiceException;
import by.kotik.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class AddToCart implements Command {
    private final String ORDER = "order";
    private final String ID_COFFEE = "idCoffee";
    private final String ID_INGREDIENT = "idIngredient";

    private final String ERROR_PATH = "error.jsp";
    private final String PATH = "Controller?command=gotowelcomepage&message=OK";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServiceProvider provider = ServiceProvider.getInstance();
        CoffeeService coffeeService = provider.getCoffeeService();
        IngredientService ingredientService = provider.getIngredientService();
        Order order;

        if (session.getAttribute(ORDER) == null) {
            order = new Order();
            session.setAttribute(ORDER, order);
        }

        order = (Order) session.getAttribute(ORDER);


        Coffee coffee;
        Ingredient ingredient;
        int idCoffee = -1;
        int idIngredient = -1;
        BigDecimal finalCost = order.getCost();

        if (request.getParameter(ID_COFFEE) != null) {
            idCoffee = Integer.parseInt(request.getParameter(ID_COFFEE));
        }

        if (request.getParameter(ID_INGREDIENT) != null) {
            idIngredient = Integer.parseInt(request.getParameter(ID_INGREDIENT));
        }

        try {
            if (idCoffee != -1) {
                Map<Integer, Integer> coffeeMap = order.getCoffeeMap();

                coffee = coffeeService.getCoffeeById(idCoffee);
                finalCost = finalCost.add(coffee.getCost());

                order.addCoffee(coffee);
                if (coffeeMap.get(coffee.getId()) == null) {
                    coffeeMap.put(coffee.getId(), 1);
                } else {
                    coffeeMap.put(coffee.getId(), coffeeMap.get(coffee.getId()) + 1);
                }

                order.setCost(finalCost);
            }

            if (idIngredient != -1) {
                Map<Integer, Integer> ingredientMap = order.getIngredientMap();

                ingredient = ingredientService.getIngredientById(idIngredient);
                finalCost = finalCost.add(ingredient.getCost());

                if (ingredientMap.get(ingredient.getId()) == null) {
                    ingredientMap.put(ingredient.getId(), 1);
                } else {
                    ingredientMap.put(ingredient.getId(), ingredientMap.get(ingredient.getId()) + 1);
                }

                order.addIngredient(ingredient);
                order.setCost(finalCost);
            }
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PATH);
        }


        response.sendRedirect(PATH);
    }
}
