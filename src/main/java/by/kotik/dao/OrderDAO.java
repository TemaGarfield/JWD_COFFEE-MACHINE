package by.kotik.dao;

import by.kotik.bean.Order;

import java.util.List;

public interface OrderDAO {
    boolean addOrder(Order order) throws DAOException;
    List<Order> getAllOrders(int idUser) throws DAOException;
}
