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
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class Logination implements Command {
    private final String LOGIN = "login";
    private final String PASSWORD = "password";
    private final String USER = "loggedUser";
    private final String ORDER = "order";
    private final String LOCALE = "locale";

    private final String MESSAGE = "message";

    private final String LOGINATION_PATH = "Controller?command=logination";
    private final String ERROR_PATH = "error.jsp";
    private final String GO_TO_ADMIN_PAGE_PATH = "Controller?command=gotoadminpage";
    private final String GO_TO_WELCOME_PAGE_PATH = "Controller?command=gotowelcomepage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(LOCALE);

        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE, locale);

        final String INVALID_PASSWORD_MESSAGE = resourceBundle.getString("locale.login.error.invalid_password_message");
        final String INVALID_INPUT_MESSAGE = resourceBundle.getString("locale.login.error.invalid_input_message");

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();

        User user;

        try {
            user = userService.authorization(login, password);
            if (user == null) {
                session.setAttribute(MESSAGE, INVALID_INPUT_MESSAGE);
                response.sendRedirect(LOGINATION_PATH);
                return;
            }

            if (login.equals(user.getUsername()) && user.getPassword().isEmpty()) {
                session.setAttribute(MESSAGE, INVALID_PASSWORD_MESSAGE);
                response.sendRedirect(LOGINATION_PATH);
                return;
            }

            if (Admin.class.equals(user.getClass())) {
                response.sendRedirect(GO_TO_ADMIN_PAGE_PATH);
                return;
            }


            Order order = new Order();

            order.setIdUser(user.getId());

            session.setAttribute(USER, user);
            session.setAttribute(ORDER, order);

            response.sendRedirect(GO_TO_WELCOME_PAGE_PATH);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PATH);
        }
    }
}
