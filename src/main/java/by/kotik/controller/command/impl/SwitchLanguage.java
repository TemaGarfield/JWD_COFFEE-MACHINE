package by.kotik.controller.command.impl;

import by.kotik.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class SwitchLanguage implements Command {
    private final String LOCALE = "locale";
    private final String EN = "en";
    private final String RU = "ru";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path").substring(request.getContextPath().length());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        HttpSession session = request.getSession();
        Locale ruLocale = new Locale(RU, RU);

        switch (request.getParameter(LOCALE)) {
            case EN:
                session.setAttribute(LOCALE, Locale.ENGLISH);
                break;
            case RU:
                session.setAttribute(LOCALE, ruLocale);
                break;
            default:
                session.setAttribute(LOCALE, Locale.getDefault());
        }

        requestDispatcher.forward(request, response);
    }
}
