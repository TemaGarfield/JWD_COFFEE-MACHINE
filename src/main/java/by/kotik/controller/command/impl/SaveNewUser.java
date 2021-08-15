package by.kotik.controller.command.impl;

import by.kotik.bean.User;
import by.kotik.controller.command.Command;
import by.kotik.service.ServiceException;
import by.kotik.service.ServiceProvider;
import by.kotik.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.ResourceBundle;


public class SaveNewUser implements Command {
    private final String LOGIN = "login";
    private final String PASSWORD = "password";
    private final String LOCALE = "locale";

    private final String MESSAGE = "message";
    private final String INVALID_INPUT = "locale.login.error.invalid_input_message";
    private final String USER_IN_DB= "locale.error.registration.user_in_db_message";

    private final String GO_TO_REGISTRATION_PAGE_PATH = "Controller?command=registration";
    private final String GO_TO_MAIN_PAGE_PATH = "Controller?command=gotomainpage";
    private final String ERROR_PATH = "error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(LOCALE);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE, locale);

        final String INVALID_INPUT_MESSAGE = resourceBundle.getString(INVALID_INPUT);
        final String USER_IN_DB_MESSAGE = resourceBundle.getString(USER_IN_DB);

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        BigDecimal balance = new BigDecimal(0.00);

        User user = new User(login, password, balance);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();

        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            session.setAttribute(MESSAGE, INVALID_INPUT_MESSAGE);
            response.sendRedirect(GO_TO_REGISTRATION_PAGE_PATH);
            return;
        }
        
        try {
            if (!userService.isUserInDB(login)) {
                session.setAttribute(MESSAGE, USER_IN_DB_MESSAGE);
                response.sendRedirect(GO_TO_REGISTRATION_PAGE_PATH);
                return;
            }

            userService.registration(user);
            response.sendRedirect(GO_TO_MAIN_PAGE_PATH);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PATH);
        }
    }
}
