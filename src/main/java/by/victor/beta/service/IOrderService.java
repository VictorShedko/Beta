package by.victor.beta.service;

import by.victor.beta.entity.Order;
import by.victor.beta.entity.util.OrderStatus;
import by.victor.beta.entity.User;

import java.util.List;

public interface IOrderService {
    void addOrder(Order order) throws ServiceException;

    List<Order> findOrderByCustomer(String customerName) throws ServiceException;

    List<Order> findOrderByExecutor(String executorName) throws ServiceException;

    List<Order> findOrderById(long orderId) throws ServiceException;

    List<Order> findOrderByStatus(OrderStatus status) throws ServiceException;

    void acceptOrder(Order order, User user) throws ServiceException;

    boolean updateOrderStatus(Order order, OrderStatus newStatus, long deanedTime) throws ServiceException;

    void timeUpdate();


}
