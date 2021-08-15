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

public class GoToEditUser implements Command {
    private final String ID = "id";
    private final String USER = "user";

    private final String PATH = "/WEB-INF/jsp/edit-user.jsp";
    private final String ERROR_PATH = "error.jsp";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(ID));

        HttpSession session = request.getSession();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);

        ServiceProvider provider = ServiceProvider.getInstance();
        AdminService adminService = provider.getAdminService();


        try {
            session.setAttribute(USER, adminService.getUserById(id));
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PATH);
        }
    }
}
