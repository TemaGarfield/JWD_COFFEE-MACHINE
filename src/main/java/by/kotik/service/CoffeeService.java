package by.kotik.service;

import by.kotik.bean.Coffee;

import java.util.List;

public interface CoffeeService {
    List<Coffee> all() throws ServiceException;
}
