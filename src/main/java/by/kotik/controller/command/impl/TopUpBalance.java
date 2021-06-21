package by.kotik.controller.command.impl;

import by.kotik.bean.User;
import by.kotik.controller.command.Command;
import by.kotik.service.ServiceException;
import by.kotik.service.ServiceProvider;
import by.kotik.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class TopUpBalance implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("loggedUser");
        BigDecimal money = new BigDecimal(request.getParameter("money"));
        BigDecimal finalBalance = user.getBalance().add(money);

        user.setBalance(finalBalance);

        try {
            userService.topUpBalance(user);
            response.sendRedirect("Controller?command=gotowelcomepage");
        } catch (ServiceException e) {
            response.sendRedirect("error.jsp");
        }
    }
}
