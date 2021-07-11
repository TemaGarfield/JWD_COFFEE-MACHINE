package by.kotik.controller.command.impl;

import by.kotik.bean.User;
import by.kotik.controller.command.Command;
import by.kotik.service.OrderService;
import by.kotik.service.ServiceException;
import by.kotik.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToShowAllOrders implements Command {
    private final String USER = "loggedUser";
    private final String ORDERS = "orders";

    private final String ALL_ORDERS_PATH = "WEB-INF/jsp/all-orders.jsp";
    private final String ERROR_PATH = "error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ALL_ORDERS_PATH);
        HttpSession session = request.getSession();

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderService orderService = serviceProvider.getOrderService();

        User user = (User) session.getAttribute(USER);

        try {
            session.setAttribute(ORDERS, orderService.getAllOrders(user.getId()));
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PATH);
        }

        requestDispatcher.forward(request, response);
    }
}
