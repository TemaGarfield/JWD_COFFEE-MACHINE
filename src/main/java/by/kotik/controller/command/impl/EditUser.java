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

public class EditUser implements Command {
    private final String ID = "id";
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String BALANCE = "balance";
    private final String LOCALE = "locale";

    private final String MESSAGE = "message";

    private final String PATH = "/WEB-INF/jsp/edit-user.jsp";
    private final String EDIT_USERS_PATH = "Controller?command=gotoeditusers";
    private final String ERROR_PATH = "error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
        HttpSession session = request.getSession();

        Locale locale = (Locale) session.getAttribute(LOCALE);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE, locale);

        int id = Integer.parseInt(request.getParameter(ID).trim());
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        BigDecimal balance = new BigDecimal(request.getParameter(BALANCE));

        ServiceProvider provider = ServiceProvider.getInstance();
        AdminService adminService = provider.getAdminService();

        try {
            String message = adminService.updateUser(id, username, password, balance);

            if (!message.isEmpty()) {
                session.setAttribute(MESSAGE, resourceBundle.getString(message));
                requestDispatcher.forward(request, response);
                return;
            }

            adminService.updateUser(id, username, password, balance);
            response.sendRedirect(EDIT_USERS_PATH);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PATH);
        }
    }
}
