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

public class SaveNewCoffee implements Command {

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

    private final String ADMIN_PATH = "/WEB-INF/jsp/admin.jsp";
    private final String GO_TO_ADMIN_PAGE_PATH = "Controller?command=gotoadminpage&message=OK";
    private final String ERROR_PATH = "error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADMIN_PATH);
        Locale locale = (Locale) session.getAttribute(LOCALE);
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

        String type = request.getParameter(TYPE);
        BigDecimal cost = new BigDecimal(request.getParameter(COST));
        Integer amount = Integer.valueOf(request.getParameter(AMOUNT));

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

        Coffee coffee = new Coffee(type, cost, amount);

        ServiceProvider provider = ServiceProvider.getInstance();
        CoffeeService coffeeService = provider.getCoffeeService();

        try {
            coffeeService.addCoffee(coffee);
            response.sendRedirect(GO_TO_ADMIN_PAGE_PATH);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PATH);
        }
    }
}
