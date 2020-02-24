package by.victor.beta.service.impl;

import by.victor.beta.entity.*;

import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.impl.OrderRepository;
import by.victor.beta.repository.impl.UserRepository;
import by.victor.beta.repository.specification.impl.orderspecification.*;
import by.victor.beta.service.IOrderService;
import by.victor.beta.service.util.NotifyMessageBuilder;
import by.victor.beta.service.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class OrderService implements IOrderService {
    public static final Logger logger= LogManager.getLogger(OrderService.class);
    private NotifyMessageBuilder notifyMessageBuilder=new NotifyMessageBuilder();
    private static final ReentrantLock orderLock=new ReentrantLock();
    @Override
    public void addOrder(Order order) throws ServiceException {
        AddOrderSpecification specification=new AddOrderSpecification(order);
        try {
            UserRepository.getInstance().createQuery(specification);
            logger.log(Level.TRACE,"add order "+order);
            OrderManager.INSTANCE.startTimer();
        } catch (RepositoryException e) {
            logger.log(Level.ERROR,"add error",e);
            throw new ServiceException(e);
        }

    }

    @Override
    public List<Order> findOrderByCustomer(String customerName) throws ServiceException {
        FindOrderByCustomerSpecification specification= new FindOrderByCustomerSpecification(customerName);
        try {
            List<Order> orders=OrderRepository.getInstance().findQuery(specification);
            return orders;
        } catch ( RepositoryException e) {
            logger.log(Level.ERROR,"add error",e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findOrderByExecutor(String executorName) throws ServiceException {
        FindOrderByExecutorSpecification specification=new FindOrderByExecutorSpecification(executorName);
        try {
            List<Order> orders=OrderRepository.getInstance().findQuery(specification);
            return orders;
        } catch ( RepositoryException e) {
            logger.log(Level.ERROR,"find order by status",e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findOrderById(long orderId) throws ServiceException {
        FindOrderById specification=new FindOrderById(orderId);
        try {
            List<Order> orders=OrderRepository.getInstance().findQuery(specification);
            return orders;
        } catch ( RepositoryException e) {
            logger.log(Level.ERROR,"find order by id",e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findOrderByStatus(OrderStatus status) throws ServiceException {
        FindOrderByStatusSpecification specification=new FindOrderByStatusSpecification(status.ordinal());
        try {
            List<Order> orders=OrderRepository.getInstance().findQuery(specification);
            return orders;
        } catch ( RepositoryException e) {
            logger.log(Level.ERROR,"find order by status",e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void acceptOrder(Order order, User user) throws ServiceException {
        orderLock.lock();
        try {


            order.setExecutorId(user.getId());
            order.setStatus(OrderStatus.ACCEPTED);
            ChangeOrderSpecification specification = new ChangeOrderSpecification(order);
            try {
                UserRepository.getInstance().updateQuery(specification);
            } catch (RepositoryException e) {
                throw new ServiceException();
            }
        }finally {
            orderLock.unlock();
        }


    }

    @Override
    public boolean updateOrderStatus(Order order, OrderStatus newStatus, long deanedTime) throws ServiceException {
        orderLock.lock();
        try {


            Order currentOrder = findOrderById(order.getOrderId()).get(0);
            if (currentOrder.getStartTime().getTime() - System.currentTimeMillis() - deanedTime > 0) {
                order.setStatus(newStatus);
                ChangeOrderSpecification specification =
                        new ChangeOrderSpecification(order);
                try {
                    UserRepository.getInstance().updateQuery(specification);
                    return true;
                } catch (RepositoryException e) {
                    throw new ServiceException();
                }
            } else {
                return false;
            }
        }finally {
            orderLock.unlock();
        }
    }

    @Override
    public void timeUpdate() {

        FindDeprecatedOrdersSpecification specification=new FindDeprecatedOrdersSpecification();
        try {
            UserService userService=new UserService();//todo куда что лучше выносить
            NotifyService notifyService=new NotifyService();
            List<Order> orders =OrderRepository.getInstance().findQuery(specification);
            String notifyText;
            for (Order order:orders) {
                User customer=userService.findUserByUsername(order.getCustomer()).get(0);

                switch (order.getStatus()) {
                    case NEW: {
                        order.setStatus(OrderStatus.NOT_CLAIMED);
                        notifyText=notifyMessageBuilder.orderNotClaimedMessage(customer,order);
                         notifyService.addNotify(notifyText,customer,NotifyType.ORDER_NOT_CLAIMED);
                         logger.log(Level.DEBUG,"change status order new->not claimed"+order.toString());
                    }
                    break;
                    case ACCEPTED: {
                        order.setStatus(OrderStatus.IN_PROGRESS);
                        User executor=userService.findUserByUsername(order.getExecutor()).get(0);
                        notifyText=notifyMessageBuilder.orderExecutionStartMessage(customer,executor,order);
                        notifyService.addNotify(notifyText,customer,NotifyType.ORDER_EXECUTION_START);
                        logger.log(Level.DEBUG,"change status order accepted->in progress"+order.toString());
                    }
                    break;
                    case IN_PROGRESS: {
                        order.setStatus(OrderStatus.COMPLETED);
                        User executor=userService.findUserByUsername(order.getExecutor()).get(0);
                        userService.creditUser(executor,order.getPrice());
                        notifyText=notifyMessageBuilder.orderExecutionFinishToCustomer(customer,executor,order);
                        userService.creditUser(executor,order.getPrice());
                        notifyService.addNotify(notifyText,customer,NotifyType.ORDER_EXECUTION_FINISH_TO_EXECUTOR);
                        notifyText=notifyMessageBuilder.orderExecutionFinishToExecutor(customer,executor,order);//todo
                        notifyService.addNotify(notifyText,customer,NotifyType.ORDER_EXECUTION_FINISH_TO_CUSTOER);
                        logger.log(Level.DEBUG,"change status order accepted->completed"+order.toString());
                    }
                    break;
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
