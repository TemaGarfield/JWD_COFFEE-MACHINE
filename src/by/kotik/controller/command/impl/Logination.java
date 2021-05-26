package by.kotik.controller.command.impl;

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

        User user = null;
        //RequestDispatcher requestDispatcher = null;

        try {
            user = userService.authorization(login, password);
            if (user == null) {
                response.sendRedirect("error.jsp");
                return;
            }

            response.sendRedirect("Controller?command=gotomainpage");
        } catch (ServiceException e) {
            response.sendRedirect("error.jsp");
            System.out.println("Service exception");
        }
    }
}
