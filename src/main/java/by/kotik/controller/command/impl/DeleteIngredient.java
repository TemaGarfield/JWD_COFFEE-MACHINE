package by.kotik.controller.command.impl;

import by.kotik.controller.command.Command;
import by.kotik.service.IngredientService;
import by.kotik.service.ServiceException;
import by.kotik.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteIngredient implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ServiceProvider provider = ServiceProvider.getInstance();
        IngredientService ingredientService = provider.getIngredientService();

        try {
            ingredientService.deleteIngredient(id);
            response.sendRedirect("Controller?command=gotoadminpage");
        } catch (ServiceException e) {
            response.sendRedirect("error.jsp");
        }
    }
}
