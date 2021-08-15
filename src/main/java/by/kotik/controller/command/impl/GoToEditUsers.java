package by.kotik.controller.command.impl;

import by.kotik.bean.User;
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
import java.util.List;

public class GoToEditUsers implements Command {
    private final String PATH = "/WEB-INF/jsp/edit-users.jsp";
    private final String ERROR_PATH = "error.jsp";

    private final String USERS = "users";
    private final String USER = "loggedUser";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
        HttpSession session = request.getSession();

        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");

        if (session.getAttribute(USER) == null) {
            response.sendRedirect(ERROR_PATH);
            return;
        }

        ServiceProvider provider = ServiceProvider.getInstance();
        AdminService adminService = provider.getAdminService();

        try {
            List<User> users = adminService.getAllUsers();
            session.setAttribute(USERS, users);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PATH);
        }

        requestDispatcher.forward(request, response);
    }
}
