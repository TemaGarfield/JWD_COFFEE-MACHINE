package by.kotik.controller.command.impl;

import by.kotik.controller.command.Command;
import by.kotik.service.AdminService;
import by.kotik.service.ServiceException;
import by.kotik.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.ResourceBundle;

public class SaveUserFromAdmin implements Command {
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String BALANCE = "balance";
    private final String LOCALE = "locale";

    private final String MESSAGE = "message";

    private final String PATH = "Controller?command=gotoeditusers";
    private final String ERROR_PATH = "error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
        HttpSession session = request.getSession();

        Locale locale = (Locale) session.getAttribute(LOCALE);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE, locale);

        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        String balance = request.getParameter(BALANCE);

        ServiceProvider provider = ServiceProvider.getInstance();
        AdminService adminService = provider.getAdminService();

        try {
            String message = adminService.addUser(username, password, balance);

            if (!message.isEmpty()) {
                session.setAttribute(MESSAGE, resourceBundle.getString(message));
            }

            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PATH);
        }

        response.sendRedirect(PATH);
    }
}
