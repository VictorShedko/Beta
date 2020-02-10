package by.victor.beta.service.impl;

import by.victor.beta.entity.*;

import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.impl.OrderRepository;
import by.victor.beta.repository.impl.UserRepository;
import by.victor.beta.repository.specification.impl.orderspecification.*;
import by.victor.beta.service.AbstractOrderService;
import by.victor.beta.service.CleanerEntityProvider;
import by.victor.beta.service.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class OrderService implements AbstractOrderService {
    public static final Logger logger= LogManager.getLogger(OrderService.class);
    private static final ReentrantLock orderLock=new ReentrantLock();
    @Override
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

    @Override
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

    @Override
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

    @Override
    public List<Order> findOrderById(long orderId){
        FindOrderById specification=new FindOrderById(orderId);
        try {
            List<Order> orders=OrderRepository.getInstance().findQuery(specification);
            return orders;
        } catch ( RepositoryException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public void timeUpdate() {

        FindDeprecatedOrdersSpecification specification=new FindDeprecatedOrdersSpecification();
        try {
            UserService userService=new UserService();
            NotifyService notifyService=new NotifyService();
            CleanerEntityProvider cleanerEntityProvider =new CleanerEntityProvider();
            List<Order> orders =OrderRepository.getInstance().findQuery(specification);
            NotifyMessageBuilder builder=new NotifyMessageBuilder();
            NotifyMessageBuilder notifyMessageBuilder=new NotifyMessageBuilder();
            String notifyText;
            for (Order order:orders) {
                User customer=userService.findUserByUsername(order.getCustomer()).get(0);

                switch (order.getStatus()) {
                    case NEW: {
                        order.setStatus(OrderStatus.NOT_CLAIMED);
                        notifyText=notifyMessageBuilder.orderNotClaimedMessage(customer,order);
                         notifyService.addNotify(notifyText,customer,NotifyType.ORDER_NOT_CLAIMED);
                    }
                    break;
                    case ACCEPTED: {
                        order.setStatus(OrderStatus.IN_PROGRESS);
                        User executor=userService.findUserByUsername(order.getCustomer()).get(0);
                        notifyText=notifyMessageBuilder.orderExecutionStartMessage(customer,executor,order);
                        notifyService.addNotify(notifyText,customer,NotifyType.ORDER_EXECUTION_START);
                    }
                    break;
                    case IN_PROGRESS: {
                        order.setStatus(OrderStatus.COMPLETED);
                        User executor=userService.findUserByUsername(order.getExecutor()).get(0);
                        userService.creditUser(executor,order.getPrice());
                        notifyText=notifyMessageBuilder.orderExecutionFinishMessage(customer,executor,order);
                        userService.creditUser(executor,order.getPrice());
                        notifyService.addNotify(notifyText,customer,NotifyType.ORDER_EXECUTION_FINISH);
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
