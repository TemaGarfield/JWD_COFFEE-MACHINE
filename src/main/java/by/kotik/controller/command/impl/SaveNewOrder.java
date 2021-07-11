package by.kotik.controller.command.impl;

import by.kotik.bean.Coffee;
import by.kotik.bean.Ingredient;
import by.kotik.bean.Order;
import by.kotik.bean.User;
import by.kotik.controller.command.Command;
import by.kotik.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class SaveNewOrder implements Command {
    private final String ORDER = "order";
    private final String USER = "loggedUser";
    private final String COFFEE_LIST = "coffeeList";
    private final String INGREDIENT_LIST = "ingredientList";
    private final String LOCALE = "locale";

    private final String MESSAGE = "message";

    private final String GO_TO_WELCOME_PAGE_PATH = "Controller?command=gotowelcomepage";
    private final String GO_TO_CART_PATH = "Controller?command=gotocart";
    private final String ERROR_PATH = "error.jsp";

    private final String NO_MONEY_LOCALE = "locale.error.order.no_money_message";
    private final String EMPTY_CART_LOCALE = "locale.error.order.empty_cart_message";
    private final String NO_ENOUGH_COFFEE_LOCALE = "locale.error.order.no_enough_coffee_message";
    private final String NO_ENOUGH_INGREDIENT_LOCALE = "locale.error.order.no_enough_ingredient_message";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(LOCALE);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE, locale);
        ServiceProvider provider = ServiceProvider.getInstance();
        OrderService orderService = provider.getOrderService();
        UserService userService = provider.getUserService();
        CoffeeService coffeeService = provider.getCoffeeService();
        IngredientService ingredientService = provider.getIngredientService();


        Order order = (Order) session.getAttribute(ORDER);
        User user = (User) session.getAttribute(USER);
        List<Coffee> coffeeList = (List<Coffee>) session.getAttribute(COFFEE_LIST);
        List<Ingredient> ingredientList = (List<Ingredient>) session.getAttribute(INGREDIENT_LIST);

        final String NO_MONEY_MESSAGE = resourceBundle.getString(NO_MONEY_LOCALE);
        final String EMPTY_CART_MESSAGE = resourceBundle.getString(EMPTY_CART_LOCALE);
        final String NO_ENOUGH_COFFEE_MESSAGE = resourceBundle.getString(NO_ENOUGH_COFFEE_LOCALE);
        final String NO_ENOUGH_INGREDIENT_MESSAGE = resourceBundle.getString(NO_ENOUGH_INGREDIENT_LOCALE);

        if (user.getBalance().compareTo(order.getCost()) < 0) {
            session.setAttribute(MESSAGE, NO_MONEY_MESSAGE);
            response.sendRedirect(GO_TO_CART_PATH);
            return;
        }

        if (order.getCoffeeList().isEmpty() && order.getIngredientList().isEmpty()) {
            session.setAttribute(MESSAGE, EMPTY_CART_MESSAGE);
            response.sendRedirect(GO_TO_CART_PATH);
            return;
        }

        for (Coffee coffee : coffeeList) {
            if (order.getCoffeeMap().containsKey(coffee.getId())) {
                if (order.getCoffeeMap().get(coffee.getId()) > coffee.getAmount()) {
                    session.setAttribute(MESSAGE, NO_ENOUGH_COFFEE_MESSAGE);
                    response.sendRedirect(GO_TO_CART_PATH);
                    return;
                }
            }
        }

        for (Ingredient ingredient : ingredientList) {
            if (order.getIngredientMap().containsKey(ingredient.getId())) {
                if (order.getIngredientMap().get(ingredient.getId()) > ingredient.getAmount()) {
                    session.setAttribute(MESSAGE, NO_ENOUGH_INGREDIENT_MESSAGE);
                    response.sendRedirect(GO_TO_CART_PATH);
                    return;
                }
            }
        }



        try {
            orderService.addOrder(order);

            BigDecimal finalBalance = user.getBalance().subtract(order.getCost());
            user.setBalance(finalBalance);

            userService.topUpBalance(user);

            for (Coffee coffee : coffeeList) {
                Integer coffeeInMapId = order.getCoffeeMap().get(coffee.getId());

                if (coffeeInMapId != null) {
                    int finalAmount = coffee.getAmount() - order.getCoffeeMap().get(coffee.getId());

                    coffee.setAmount(finalAmount);
                    coffeeService.updateCoffeeAmountById(coffee.getId(), coffee.getAmount());
                }
            }

            for (Ingredient ingredient : ingredientList) {
                Integer ingredientInMapId = order.getIngredientMap().get(ingredient.getId());

                if (ingredientInMapId != null) {
                    int finalAmount = ingredient.getAmount() - order.getIngredientMap().get(ingredient.getId());

                    ingredient.setAmount(finalAmount);
                    ingredientService.updateIngredientAmountById(ingredient.getId(), ingredient.getAmount());
                }
            }

            session.removeAttribute(ORDER);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PATH);
            return;
        }

        response.sendRedirect(GO_TO_WELCOME_PAGE_PATH);
    }
}
