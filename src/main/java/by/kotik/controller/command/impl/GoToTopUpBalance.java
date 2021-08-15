package by.kotik.controller.command.impl;

import by.kotik.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToTopUpBalance implements Command {

    private final String PATH = "WEB-INF/jsp/top-up-balance.jsp";
    private final String ERROR_PATH = "error.jsp";

    private final String USER = "loggedUser";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);

        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");

        if (session.getAttribute(USER) == null) {
            response.sendRedirect(ERROR_PATH);
            return;
        }

        requestDispatcher.forward(request, response);
    }
}
