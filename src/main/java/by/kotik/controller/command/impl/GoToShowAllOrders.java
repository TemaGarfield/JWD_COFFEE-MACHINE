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
    private final String PATH = "WEB-INF/jsp/all-orders.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
        HttpSession session = request.getSession();

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderService orderService = serviceProvider.getOrderService();

        User user = (User) session.getAttribute("loggedUser");

        try {
            session.setAttribute("orders", orderService.getAllOrders(user.getId()));
        } catch (ServiceException e) {
            response.sendRedirect("error.jsp");
        }

        requestDispatcher.forward(request, response);
    }
}
