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
    private final String USER = "loggedUser";
    private final String MONEY_TO_ADD = "money";

    private final String MESSAGE = "message";
    private final String INVALID_VALUE_MESSAGE = "Invalid value";

    private final String GO_TO_WELCOME_PAGE_PATH = "Controller?command=gotowelcomepage";
    private final String GO_TO_TOP_UP_BALANCE_PATH = "Controller?command=gototopupbalance";
    private final String ERROR_PATH = "error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(USER);
        BigDecimal money = new BigDecimal(request.getParameter(MONEY_TO_ADD));

        if (money.compareTo(new BigDecimal(0.01)) < 0) {
            session.setAttribute(MESSAGE, INVALID_VALUE_MESSAGE);
            response.sendRedirect(GO_TO_TOP_UP_BALANCE_PATH);
            return;
        }

        BigDecimal finalBalance = user.getBalance().add(money);

        user.setBalance(finalBalance);

        try {
            userService.topUpBalance(user);
            response.sendRedirect(GO_TO_WELCOME_PAGE_PATH);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PATH);
        }
    }
}
