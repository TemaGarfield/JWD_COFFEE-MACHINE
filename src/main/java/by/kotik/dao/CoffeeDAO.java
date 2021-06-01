package by.kotik.dao;

import by.kotik.bean.Coffee;

import java.util.List;

public interface CoffeeDAO {
    List<Coffee> all() throws DAOException;
}
