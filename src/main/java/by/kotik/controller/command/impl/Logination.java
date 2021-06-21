package by.kotik.controller.command.impl;

import by.kotik.bean.Admin;
import by.kotik.bean.Order;
import by.kotik.bean.User;
import by.kotik.controller.command.Command;
import by.kotik.service.ServiceException;
import by.kotik.service.ServiceProvider;
import by.kotik.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Logination implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();

        User user;

        try {
            user = userService.authorization(login, password);
            if (user == null) {
                response.sendRedirect("error.jsp");
                return;
            }

            if (Admin.class.equals(user.getClass())) {
                response.sendRedirect("Controller?command=gotoadminpage");
                return;
            }

            HttpSession session = request.getSession();
            Order order = new Order();

            order.setIdUser(user.getId());

            session.setAttribute("loggedUser", user);
            session.setAttribute("order", order);

            response.sendRedirect("Controller?command=gotowelcomepage");
        } catch (ServiceException e) {
            response.sendRedirect("error.jsp");
        }
    }
}
