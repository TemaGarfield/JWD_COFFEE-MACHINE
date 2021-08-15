package by.kotik.controller.command.impl;

import by.kotik.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToCart implements Command {
    private final String USER = "loggedUser";

    private final String CART_PATH = "WEB-INF/jsp/cart.jsp";
    private final String ERROR_PATH = "error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CART_PATH);
        HttpSession session = request.getSession();

        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        if (session.getAttribute(USER) == null) {
            response.sendRedirect(ERROR_PATH);
            return;
        }

        requestDispatcher.forward(request, response);
    }
}
