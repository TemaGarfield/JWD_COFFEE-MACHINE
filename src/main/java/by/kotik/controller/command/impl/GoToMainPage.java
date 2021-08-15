package by.kotik.controller.command.impl;

import by.kotik.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class GoToMainPage implements Command {
    private final String LOCALE = "locale";
    private final String RU = "ru";
    private final String EN = "en";

    private final String MAIN_PATH = "/WEB-INF/jsp/main.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(MAIN_PATH);
        HttpSession session = request.getSession();

        Locale ruLocale = new Locale(RU, RU);
        Locale.setDefault(ruLocale);

        session.setAttribute(LOCALE, Locale.getDefault());

        requestDispatcher.forward(request, response);
    }
}
