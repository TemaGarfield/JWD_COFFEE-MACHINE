package by.kotik.service.impl;

import by.kotik.bean.Ingredient;
import by.kotik.dao.DAOException;
import by.kotik.dao.DAOProvider;
import by.kotik.dao.IngredientDAO;
import by.kotik.service.IngredientService;
import by.kotik.service.ServiceException;

import java.util.List;

public class IngredientServiceImpl implements IngredientService {
    @Override
    public List<Ingredient> getAllIngredient() throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        IngredientDAO ingredientDAO = provider.getIngredientDAO();

        List<Ingredient> ingredientList;

        try {
            ingredientList = ingredientDAO.getAllIngredients();

            return ingredientList;
        } catch (DAOException e) {
            throw new ServiceException("Ingredient service error!", e);
        }
    }

    @Override
    public boolean addIngredient(Ingredient ingredient) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        IngredientDAO ingredientDAO = provider.getIngredientDAO();

        try {
            return ingredientDAO.addIngredient(ingredient);
        } catch (DAOException e) {
            throw new ServiceException("Fail to add ingredient", e);
        }
    }

    @Override
    public boolean deleteIngredient(int id) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        IngredientDAO ingredientDAO = provider.getIngredientDAO();

        try {
            return ingredientDAO.deleteIngredient(id);
        } catch (DAOException e) {
            throw new ServiceException("Fail to delete ingredient!", e);
        }
    }

    @Override
    public Ingredient getIngredientById(int id) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        IngredientDAO ingredientDAO = provider.getIngredientDAO();

        try {
            return ingredientDAO.getIngredientById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean editIngredient(Ingredient ingredient) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        IngredientDAO ingredientDAO = provider.getIngredientDAO();

        try {
            return ingredientDAO.editIngredient(ingredient);
        } catch (DAOException e) {
            throw new ServiceException("Fail to edit ingredient!", e);
        }
    }

    @Override
    public boolean updateIngredientAmountById(int id, int amount) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        IngredientDAO ingredientDAO = provider.getIngredientDAO();

        try {
            return ingredientDAO.updateIngredientAmountById(id, amount);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
