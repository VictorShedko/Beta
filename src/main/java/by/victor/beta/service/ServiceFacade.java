package by.victor.beta.service;

import by.victor.beta.entity.*;
import by.victor.beta.entity.UserStatus;
import by.victor.beta.service.document.DocumentService;
import by.victor.beta.service.notifyservice.NotifyMessageBuilder;
import by.victor.beta.service.notifyservice.NotifyService;
import by.victor.beta.service.orderservice.OrderService;
import by.victor.beta.service.userservice.UserService;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public enum ServiceFacade {
    instance;
    public static final long ONE_HOUR = TimeUnit.HOURS.toMillis(1);

    private Order findSingleOrderById(int id) throws ServiceException {
        OrderService orderService=new OrderService();
        List<Order>orders=orderService.findOrderById(id);
        if(orders.size()==1){
            return orders.get(0);
        }else {
            throw new ServiceException("order not founded");
        }
    }


    private User findSingleUser(String username) throws ServiceException {
        UserService service = new UserService();
        List<User> users = service.findUserByUsername(username);
        if (users.size() == 1) {
            return service.findUserByUsername(username).get(0);
        } else {
            throw new ServiceException();
        }
    }

    public User findUser(String username) throws ServiceException {
        User user=findSingleUser(username);
        return user;
    }

    public Optional<User> login(String login, String password) {
        UserService userAction = new UserService();
        List<User> users = userAction.findUserByLogin(login);
        if (users.size() == 1 && users.get(0).getPassword().equals(password) && users.get(0).getStatus() != UserStatus.DELETED) {
            return Optional.of(users.get(0));
        } else {
            return Optional.empty();
        }
    }

    public User registerUser(String username, String password, String login, Role role, String email) throws ServiceException {//todo level
        UserService userService = new UserService();

        List<User> users = userService.findUserByLogin(login);
        users.addAll(userService.findUserByUsername(username));
        if (users.isEmpty()) {
            CleanerFactory factory = new CleanerFactory();
            User user = factory.getUser(username, password, login, role, email, 0);
            userService.addUser(user);
            user = findSingleUser(username);
            return user;
        } else {
            throw new ServiceException();
        }

    }

    public void deleteUser(String username) throws ServiceException {
        UserService userService = new UserService();
        userService.setUserStatus(UserStatus.DELETED, username);

    }

    public List<User> showUserByRole(Role role) {
        UserService service=new UserService();
        return service.findUserByRole(role);
    }

    public List<User> showUserByStatus(UserStatus status) {
        UserService service=new UserService();
        return service.findUserByStatus(status);
    }

    public void validateUser(String username) throws ServiceException {
        UserService userService = new UserService();
        userService.setUserStatus(UserStatus.VERIFIED, username);
    }

    public boolean createOrder(String address, String description, String username, Date startTime, Date endTime, int price) throws ServiceException {//todo
        OrderService orderService = new OrderService();
        UserService userService = new UserService();
        List<User> users = userService.findUserByUsername(username);
        if (users.size() == 1) {
            User user = users.get(0);

                if (userService.debitUser(user, price)) {
                    CleanerFactory factory = new CleanerFactory();
                    Order order = factory.getOrder(username, null, price, startTime, endTime, address, description, user);
                    orderService.addOrder(order);
                    return true;
                } else {
                    return false;
                }
        } else {
            throw new ServiceException();
        }
    }

    public boolean acceptOrder(int orderId, String username) throws ServiceException {
        OrderService orderService = new OrderService();
        Order order = findSingleOrderById(orderId);
        User user = findSingleUser(username);
        orderService.acceptOrder(order, user);
        return true;
    }

    public boolean cancelOrderByCustomer(int orderId) throws ServiceException {
        OrderService orderService = new OrderService();
        Order order=findSingleOrderById(orderId);
        if(orderService.updateOrderStatus(order, OrderStatus.CANCELED, ONE_HOUR)) {
            NotifyService notifyService = new NotifyService();
            User customer = findSingleUser(order.getCustomer());
            NotifyMessageBuilder builder = new NotifyMessageBuilder();
            String customerNotifyText = builder.orderCanceledMessageToCustomer(customer);
            notifyService.addNotify(customerNotifyText, customer);
            if (order.getExecutor() != null) {
                User executor = findSingleUser(order.getExecutor());
                String executorNotifyText = builder.orderCanceledMessageToExecutor(executor);
                notifyService.addNotify(executorNotifyText, executor);
            }
            return true;
        }else {
            return false;
        }
    }

    public boolean refuseOrderByExecutor(int orderId) throws ServiceException {
        OrderService orderService = new OrderService();
        Order order=findSingleOrderById(orderId);
        if (orderService.updateOrderStatus(order, OrderStatus.NEW, ONE_HOUR)){
        NotifyService notifyService = new NotifyService();
        User customer = findSingleUser(order.getCustomer());
        NotifyMessageBuilder builder = new NotifyMessageBuilder();
        String notifyText = builder.orderRefuseMessage(customer,order);
        notifyService.addNotify(notifyText, customer);
        return true;
        }else {
            return true;
        }

    }


    public List<Notify> showNotifyList(String username) throws ServiceException {
        NotifyService notifyService = new NotifyService();
        List<Notify> notifies = null;
        notifies = notifyService.findNotifies(username);

        return notifies;

    }

    public List<Order> showCustomerOrderHistory(String username) {
        List<Order> orders;
        OrderService findAction = new OrderService();
        orders = findAction.findOrderByCustomer(username);
        return orders;
    }

    public List<Order> showExecutorOrderHistory(String username) {
        List<Order> orders;
        OrderService findAction = new OrderService();
        orders = findAction.findOrderByExecutor(username);
        return orders;
    }

    public List<Order> showAvailableOrder() {
        List<Order> orders;
        OrderService findAction = new OrderService();
        orders = findAction.findOrderByStatus(OrderStatus.NEW);
        return orders;
    }


    public void uploadPhoto(File file, String username) throws ServiceException {
        UserService userService = new UserService();
        userService.setUserPhoto(file, username);


    }


    public void creditAccount(String username, int sum) throws ServiceException {
        UserService service = new UserService();
        User user = service.findUserByUsername(username).get(0);
        service.creditUser(user, sum);

    }

    public void addNotify(String text, String username) throws ServiceException {
        NotifyService notifyService = new NotifyService();
        UserService userService = new UserService();
        User user = userService.findUserByUsername(username).get(0);
        notifyService.addNotify(text, user);
    }

    public void addDocument(File file, String username) throws ServiceException {

        DocumentService documentService = new DocumentService();
        User user = findSingleUser(username);
        documentService.addDocument(user, file);

    }


}
