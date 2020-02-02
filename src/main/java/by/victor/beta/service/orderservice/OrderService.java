package by.victor.beta.service.orderservice;

import by.victor.beta.entity.*;

import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.impl.OrderRepository;
import by.victor.beta.repository.impl.UserRepository;
import by.victor.beta.repository.specification.impl.orderspecification.*;
import by.victor.beta.service.CleanerFactory;
import by.victor.beta.service.notifyservice.NotifyMessageBuilder;
import by.victor.beta.service.notifyservice.NotifyService;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.userservice.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.util.List;

public class OrderService{
    public static final Logger logger= LogManager.getLogger(OrderService.class);

    public void addOrder(Order order) throws ServiceException {
        AddOrderSpecification specification=new AddOrderSpecification(order);
        try {
            UserRepository.getInstance().createQuery(specification);
            OrderManager.instance.startTimer();
        } catch (RepositoryException e) {
            logger.log(Level.ERROR,"add error",e);
            throw new ServiceException();
        }

    }

    public List<Order> findOrderByCustomer(String customerName){
        FindOrderByCustomerSpecification specification= new FindOrderByCustomerSpecification(customerName);
        try {
            List<Order> orders=OrderRepository.getInstance().findQuery(specification);
            return orders;
        } catch ( RepositoryException e) {
            logger.log(Level.ERROR,"add error",e);
            return List.of();
        }
    }

    public List<Order> findOrderByExecutor(String executorName){
        FindOrderByExecutorSpecification specification=new FindOrderByExecutorSpecification(executorName);
        try {
            List<Order> orders=OrderRepository.getInstance().findQuery(specification);
            return orders;
        } catch ( RepositoryException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<Order> findOrderById(int orderId){
        FindOrderById specification=new FindOrderById(orderId);
        try {
            List<Order> orders=OrderRepository.getInstance().findQuery(specification);
            return orders;
        } catch ( RepositoryException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<Order> findOrderByStatus(OrderStatus status){
        FindOrderByStatusSpecification specification=new FindOrderByStatusSpecification(status.ordinal());
        try {
            List<Order> orders=OrderRepository.getInstance().findQuery(specification);
            return orders;
        } catch ( RepositoryException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public void acceptOrder(Order order, User user) throws ServiceException {
        order.setExecutorId(user.getId());
        order.setStatus(OrderStatus.ACCEPTED);
        ChangeOrderSpecification specification= new ChangeOrderSpecification(order);
        try {
            UserRepository.getInstance().updateQuery(specification);
        } catch (RepositoryException e) {
            throw new ServiceException();
        }


    }

    public boolean updateOrderStatus(Order order, OrderStatus newStatus, long deanedTime) throws ServiceException {

        Order currentOrder=findOrderById(order.getOrderId()).get(0);
        if(currentOrder.getStartTime().getTime()-System.currentTimeMillis()-deanedTime>0) {
            order.setStatus(newStatus);
            ChangeOrderSpecification specification =
                    new ChangeOrderSpecification(order);
            try {
                UserRepository.getInstance().updateQuery(specification);
                return true;
            } catch (RepositoryException e) {
                throw new ServiceException();
            }
        }else {
            return false;
        }
    }

    public void timeUpdate() {

        FindDeprecatedOrdersSpecification specification=new FindDeprecatedOrdersSpecification();
        try {
            UserService userService=new UserService();
            NotifyService notifyService=new NotifyService();
            CleanerFactory cleanerFactory=new CleanerFactory();
            List<Order> orders =OrderRepository.getInstance().findQuery(specification);
            NotifyMessageBuilder builder=new NotifyMessageBuilder();
            NotifyMessageBuilder notifyMessageBuilder=new NotifyMessageBuilder();
            String notifyText;
            for (Order order:orders) {
                User customer=userService.findUserByUsername(order.getCustomer()).get(0);

                switch (order.getStatus()) {
                    case NEW: {
                        order.setStatus(OrderStatus.NOT_CLAIMED);
                        notifyText=notifyMessageBuilder.orderValuesNotClaimedMessage(customer,order);
                         notifyService.addNotify(notifyText,customer);
                    }
                    break;
                    case ACCEPTED: {
                        order.setStatus(OrderStatus.IN_PROGRESS);
                        User executor=userService.findUserByUsername(order.getCustomer()).get(0);
                        notifyText=notifyMessageBuilder.orderExecutionStartMessage(customer,executor,order);
                        notifyService.addNotify(notifyText,customer);
                    }
                    break;
                    case IN_PROGRESS: {
                        order.setStatus(OrderStatus.COMPLETED);
                        User executor=userService.findUserByUsername(order.getExecutor()).get(0);
                        userService.creditUser(executor,order.getPrice());
                        notifyText=notifyMessageBuilder.orderExecutionFinishMessage(customer,executor,order);
                        userService.creditUser(executor,order.getPrice());
                        notifyService.addNotify(notifyText,customer);
                    } break;
                }
               updateOrder(order);
            }
        } catch (RepositoryException | ServiceException e) {
       logger.log(Level.ERROR,"data base update error");
        }
    }

    private void updateOrder(Order order) throws ServiceException {
        ChangeOrderSpecification specification =
                new ChangeOrderSpecification(order);
        try {
            UserRepository.getInstance().updateQuery(specification);
        } catch (RepositoryException e) {
            throw new ServiceException();
        }
    }


}
