package by.kotik.controller.command.impl;

import by.kotik.bean.Admin;
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
import java.io.PrintWriter;

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
            session.setAttribute("loggedUser", user);

            response.sendRedirect("Controller?command=gotowelcomepage");
        } catch (ServiceException e) {
            response.sendRedirect("error.jsp");
        }
    }
}
