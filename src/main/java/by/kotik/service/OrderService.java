package by.kotik.service;

import by.kotik.bean.Order;

import java.util.List;

public interface OrderService {
    boolean addOrder(Order order) throws ServiceException;
    List<Order> getAllOrders(int idUser) throws ServiceException;
}
