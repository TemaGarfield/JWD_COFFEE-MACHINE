package by.kotik.service;

import by.kotik.bean.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getAllIngredient() throws ServiceException;
    boolean addIngredient(Ingredient ingredient) throws ServiceException;
    boolean deleteIngredient(int id) throws ServiceException;
    Ingredient getIngredientById(int id) throws ServiceException;
    boolean editIngredient(Ingredient ingredient) throws ServiceException;
}
