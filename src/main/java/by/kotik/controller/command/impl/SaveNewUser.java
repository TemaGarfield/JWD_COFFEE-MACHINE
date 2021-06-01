package by.kotik.controller.command.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.kotik.bean.User;
import by.kotik.controller.command.Command;
import by.kotik.dao.ConnectionProvider;
import by.kotik.dao.UserDatabase;
import by.kotik.service.ServiceException;
import by.kotik.service.ServiceProvider;
import by.kotik.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;


public class SaveNewUser implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        BigDecimal balance = new BigDecimal(0.00);

        User user = new User(login, password, balance);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();

        try {
            userService.registration(user);
            response.sendRedirect("Controller?command=gotomainpage&message=OK");
        } catch (ServiceException e) {
            response.sendRedirect("error.jsp");
        }
    }
}
