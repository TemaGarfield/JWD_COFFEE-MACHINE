package by.kotik.service.impl;

import by.kotik.bean.Order;
import by.kotik.dao.DAOException;
import by.kotik.dao.DAOProvider;
import by.kotik.dao.OrderDAO;
import by.kotik.service.OrderService;
import by.kotik.service.ServiceException;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public boolean addOrder(Order order) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        OrderDAO orderDAO = provider.getOrderDAO();

        try {
            orderDAO.addOrder(order);
            return true;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getAllOrders(int idUser) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        OrderDAO orderDAO = provider.getOrderDAO();

        try {
            return orderDAO.getAllOrders(idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
