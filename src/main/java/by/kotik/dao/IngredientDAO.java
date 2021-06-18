package by.kotik.dao;

import by.kotik.bean.Ingredient;

import java.util.List;

public interface IngredientDAO {
    List<Ingredient> getAllIngredients() throws DAOException;
    boolean addIngredient(Ingredient ingredient) throws DAOException;
    boolean deleteIngredient(int id) throws DAOException;
    Ingredient getIngredientById(int id) throws DAOException;
    boolean editIngredient(Ingredient ingredient) throws DAOException;
}
