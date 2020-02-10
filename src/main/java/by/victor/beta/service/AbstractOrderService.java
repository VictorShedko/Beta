package by.victor.beta.service;

import by.victor.beta.entity.Order;
import by.victor.beta.entity.OrderStatus;
import by.victor.beta.entity.User;

import java.util.List;

public interface AbstractOrderService  {
    void addOrder(Order order) throws ServiceException;

    List<Order> findOrderByCustomer(String customerName);

    List<Order> findOrderByExecutor(String executorName);

    List<Order> findOrderById(long orderId);

    List<Order> findOrderByStatus(OrderStatus status);

    void acceptOrder(Order order, User user) throws ServiceException;

    boolean updateOrderStatus(Order order, OrderStatus newStatus, long deanedTime) throws ServiceException;

    void timeUpdate();


}
