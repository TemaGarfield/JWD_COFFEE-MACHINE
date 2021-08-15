package by.kotik.controller.command.impl;

import by.kotik.controller.command.Command;
import by.kotik.service.AdminService;
import by.kotik.service.ServiceException;
import by.kotik.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUser implements Command {
    private final String COMMAND = "Controller?command=gotoeditusers";

    private final String ERROR_PATH = "error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ServiceProvider provider = ServiceProvider.getInstance();
        AdminService adminService = provider.getAdminService();

        try {
            adminService.deleteUser(id);
            response.sendRedirect(COMMAND);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PATH);
        }
    }
}
