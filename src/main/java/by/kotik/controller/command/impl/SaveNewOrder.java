package by.kotik.controller.command.impl;

import by.kotik.bean.Coffee;
import by.kotik.bean.Ingredient;
import by.kotik.bean.Order;
import by.kotik.bean.User;
import by.kotik.controller.command.Command;
import by.kotik.dao.impl.SQLOrderDAO;
import by.kotik.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class SaveNewOrder implements Command {
    private final String ORDER = "order";
    private final String USER = "loggedUser";

    private final String PATH = "Controller?command=gotowelcomepage";
    private final String ERROR_PATH = "error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServiceProvider provider = ServiceProvider.getInstance();
        OrderService orderService = provider.getOrderService();
        UserService userService = provider.getUserService();
        CoffeeService coffeeService = provider.getCoffeeService();
        IngredientService ingredientService = provider.getIngredientService();

        Order order = (Order) session.getAttribute(ORDER);
        User user = (User) session.getAttribute(USER);
        List<Coffee> coffeeList = (List<Coffee>) session.getAttribute("coffeeList");
        List<Ingredient> ingredientList = (List<Ingredient>) session.getAttribute("ingredientList");

        if (user.getBalance().compareTo(order.getCost()) < 0) {
            session.setAttribute("message", "You haven't got enough money.");
            response.sendRedirect("Controller?command=gotocart&message=noMoney");
            return;
        }


        try {
            orderService.addOrder(order);

            BigDecimal finalBalance = user.getBalance().subtract(order.getCost());
            user.setBalance(finalBalance);

            userService.topUpBalance(user);

            for (Coffee coffee : coffeeList) {
                if (order.getCoffeeMap().get(coffee.getId()) != null) {
                    coffee.setAmount(coffee.getAmount() - order.getCoffeeMap().get(coffee.getId()));
                    coffeeService.updateCoffeeAmountById(coffee.getId(), coffee.getAmount());
                }
            }

            for (Ingredient ingredient : ingredientList) {
                if (order.getIngredientMap().get(ingredient.getId()) != null) {
                    ingredient.setAmount(ingredient.getAmount() - order.getIngredientMap().get(ingredient.getId()));
                    ingredientService.updateIngredientAmountById(ingredient.getId(), ingredient.getAmount());
                }
            }

            session.removeAttribute(ORDER);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PATH);
            return;
        }

        response.sendRedirect(PATH);
    }
}
