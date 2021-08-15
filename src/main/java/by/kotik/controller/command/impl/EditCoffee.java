package by.kotik.controller.command.impl;

import by.kotik.bean.Coffee;
import by.kotik.controller.command.Command;
import by.kotik.service.CoffeeService;
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

public class EditCoffee implements Command {
    private final String ID = "id";
    private final String TYPE = "type";
    private final String COST = "cost";
    private final String AMOUNT = "amount";
    private final String LOCALE = "locale";

    private final String MESSAGE = "message";
    private final String ERROR_COST = "locale.error.edit.error_cost_message";
    private final String ERROR_AMOUNT = "locale.error.edit.error_amount_message";
    private final String ERROR_TYPE = "locale.error.edit.error_type_message";
    private final String ERROR_EMPTY_COST = "locale.error.edit.error_empty_cost_message";
    private final String ERROR_EMPTY_AMOUNT = "locale.error.edit.error_empty_amount_message";

    private final String GO_TO_ADMIN_PAGE_PATH = "Controller?command=gotoadminpage";
    private final String EDIT_COFFEE_PATH = "/WEB-INF/jsp/edit-coffee.jsp";
    private final String ERROR_PATH = "error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(LOCALE);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(EDIT_COFFEE_PATH);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE, locale);

        final String ERROR_COST_MESSAGE = resourceBundle.getString(ERROR_COST);
        final String ERROR_AMOUNT_MESSAGE = resourceBundle.getString(ERROR_AMOUNT);
        final String ERROR_TYPE_MESSAGE = resourceBundle.getString(ERROR_TYPE);
        final String ERROR_EMPTY_COST_MESSAGE = resourceBundle.getString(ERROR_EMPTY_COST);
        final String ERROR_EMPTY_AMOUNT_MESSAGE = resourceBundle.getString(ERROR_EMPTY_AMOUNT);

        if (request.getParameter(TYPE).isBlank()) {
            session.setAttribute(MESSAGE, ERROR_TYPE_MESSAGE);
            requestDispatcher.forward(request, response);
            return;
        }

        if (request.getParameter(COST).isBlank()) {
            session.setAttribute(MESSAGE, ERROR_EMPTY_COST_MESSAGE);
            requestDispatcher.forward(request, response);
            return;
        }

        if (request.getParameter(AMOUNT).isBlank()) {
            session.setAttribute(MESSAGE, ERROR_EMPTY_AMOUNT_MESSAGE);
            requestDispatcher.forward(request, response);
            return;
        }

        int id = Integer.parseInt(request.getParameter(ID));
        String type = request.getParameter(TYPE);
        BigDecimal cost = new BigDecimal(request.getParameter(COST));
        int amount = Integer.valueOf(request.getParameter(AMOUNT));

        Coffee coffee = new Coffee(id, type, cost, amount);

        if (amount < 0) {
            session.setAttribute(MESSAGE, ERROR_AMOUNT_MESSAGE);
            requestDispatcher.forward(request, response);
            return;
        }

        if (cost.compareTo(new BigDecimal(0.01)) < 0) {
            session.setAttribute(MESSAGE, ERROR_COST_MESSAGE);
            requestDispatcher.forward(request, response);
            return;
        }

        ServiceProvider provider = ServiceProvider.getInstance();
        CoffeeService service = provider.getCoffeeService();

        try {
            service.editCoffee(coffee);
            response.sendRedirect(GO_TO_ADMIN_PAGE_PATH);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PATH);
        }
    }
}
