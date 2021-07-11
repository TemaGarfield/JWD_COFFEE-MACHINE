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

public class CartDeleteCoffee implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/cart.jsp");
        HttpSession session = request.getSession();

        int index = Integer.parseInt(request.getParameter("indexCoffee"));
        Order order = (Order) session.getAttribute("order");
        BigDecimal nowCost = order.getCost();
        BigDecimal coffeeCost = order.getCoffeeList().get(index).getCost();
        BigDecimal newCost = nowCost.subtract(coffeeCost);

        order.setCost(newCost);
        order.getCoffeeList().remove(index);

        requestDispatcher.forward(request, response);
    }
}
