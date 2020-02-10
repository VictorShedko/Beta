package by.victor.beta.service;

import by.victor.beta.entity.*;
import by.victor.beta.entity.UserStatus;
import by.victor.beta.service.impl.DocumentService;
import by.victor.beta.service.impl.NotifyMessageBuilder;
import by.victor.beta.service.impl.NotifyService;
import by.victor.beta.service.impl.OrderService;
import by.victor.beta.service.impl.UserService;
import by.victor.beta.service.mail.MailService;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public enum ServiceFacade {
    instance;
    private final AbstractUserService userService=new UserService();
    private final AbstractOrderService orderService=new OrderService();
    private final AbstractNotifyService notifyService=new NotifyService();
    private final AbstractDocumentService documentService=new DocumentService();
    private final NotifyMessageBuilder notifyMessageBuilder = new NotifyMessageBuilder();
    private static final ReentrantLock paymentLock=new ReentrantLock();
    public static final long ONE_HOUR = TimeUnit.HOURS.toMillis(1);

    private Order findSingleOrderById(int id) throws ServiceException {
        List<Order> orders = orderService.findOrderById(id);
        if (orders.size() == 1) {
            return orders.get(0);
        } else {
            throw new ServiceException("order not founded");
        }
    }


    private User findSingleUser(String username) throws ServiceException {

        List<User> users = userService.findUserByUsername(username);
        if (users.size() == 1) {
            return users.get(0);
        } else {
            throw new ServiceException();
        }
    }

    public User findUserByUsername(String username) throws ServiceException {
        User user = findSingleUser(username);
        return user;
    }

    public Optional<User> login(String login, String password) {
        List<User> users = userService.findUserByLogin(login);
        if (users.size() == 1 && users.get(0).getPassword().equals(password) &&
                users.get(0).getStatus() != UserStatus.DELETED) {
            return Optional.of(users.get(0));
        } else {
            return Optional.empty();
        }
    }

    public User registerUser(String username, String password, String login, Role role, String email)
            throws ServiceException {
        List<User> users = userService.findUserByLogin(login);
        users.addAll(userService.findUserByUsername(username));
        if (users.isEmpty()) {
            CleanerEntityProvider factory = new CleanerEntityProvider();
            User user = factory.getUser(username, password, login, role, email, 0);
            userService.addUser(user);
            user = findSingleUser(username);
            MailService.instance.sendMessage("ku",user.getEmail());
            return user;
        } else {
            throw new ServiceException();
        }

    }

    public void deleteUser(String username) throws ServiceException {
        userService.setUserStatus(UserStatus.DELETED, username);

    }

    public List<User> showUserByRole(Role role) {
        return userService.findUserByRole(role);
    }

    public List<User> showUserByStatus(UserStatus status) {

        return userService.findUserByStatus(status);
    }

    public void validateUser(String username) throws ServiceException {

        userService.setUserStatus(UserStatus.VERIFIED, username);
    }

    public boolean createOrder(String address, String description, String username, Date startTime, Date endTime, int price) throws ServiceException {//todo
        List<User> users = userService.findUserByUsername(username);
        try {
            paymentLock.lock();

            if (users.size() == 1) {
                User user = users.get(0);

                if (userService.debitUser(user, price)) {
                    CleanerEntityProvider factory = new CleanerEntityProvider();
                    Order order = factory.getOrder(username, null, price, startTime, endTime, address, description, user);
                    orderService.addOrder(order);

                    String notifyText = notifyMessageBuilder.orderCreateMessage(user, order);
                    notifyService.addNotify(notifyText,user,NotifyType.ORDER_CREATED);
                    return true;
                } else {
                    return false;
                }
            } else {
                throw new ServiceException();
            }
        }finally {
            paymentLock.unlock();
        }
    }

    public boolean acceptOrder(int orderId, String executorName) throws ServiceException {
        try {
            paymentLock.lock();

            Order order = findSingleOrderById(orderId);
            User executor = findSingleUser(executorName);
            orderService.acceptOrder(order, executor);
            NotifyMessageBuilder builder = new NotifyMessageBuilder();
            User customer = findSingleUser(order.getCustomer());
            String text = builder.orderAcceptedMessage(customer, executor, order);
            NotifyType type = NotifyType.ORDER_ACCEPTED;
            notifyService.addNotify(text, customer, type);
            return true;
        }finally {
            paymentLock.unlock();
        }
    }

    public boolean cancelOrderByCustomer(int orderId) throws ServiceException {
        try {
            paymentLock.lock();

            Order order = findSingleOrderById(orderId);
            if (orderService.updateOrderStatus(order, OrderStatus.CANCELED, ONE_HOUR)) {
                User customer = findSingleUser(order.getCustomer());
                userService.creditUser(customer, order.getPrice());
                NotifyMessageBuilder builder = new NotifyMessageBuilder();
                String customerNotifyText = builder.orderCanceledMessageToCustomer(customer);
                notifyService.addNotify(customerNotifyText, customer, NotifyType.ORDER_CANCEL_TO_CUSTOMER);
                if (order.getExecutor() != null) {
                    User executor = findSingleUser(order.getExecutor());
                    String executorNotifyText = builder.orderCanceledMessageToExecutor(executor);
                    notifyService.addNotify(executorNotifyText, executor, NotifyType.ORDER_CANCEL_TO_EXECUTOR);
                }
                return true;
            } else {
                return false;
            }
        }finally {
            paymentLock.unlock();
        }
    }

    public boolean refuseOrderByExecutor(int orderId) throws ServiceException {
        Order order = findSingleOrderById(orderId);
        if (orderService.updateOrderStatus(order, OrderStatus.NEW, ONE_HOUR)) {
            User customer = findSingleUser(order.getCustomer());
            NotifyMessageBuilder builder = new NotifyMessageBuilder();
            String notifyText = builder.orderRefuseMessage(customer, order);
            notifyService.addNotify(notifyText, customer,NotifyType.ORDER_EXECUTOR_REFUSE);
            return true;
        } else {
            return true;
        }

    }

public List<Document> showUserDocument(String username) throws ServiceException {

        return documentService.getUserDocuments(username);
}

public void checkDocument(int id,String adminname) throws ServiceException {
    User admin=findSingleUser(adminname);
    Document document=documentService.findDocumentById(id);
    documentService.checkDocument(admin,document);
}

    public List<Notification> showNotifyList(String username) throws ServiceException {
        List<Notification> notifies = null;
        notifies = notifyService.findNotifies(username);
        return notifies;
    }

    public List<Order> showCustomerOrderHistory(String username) {
        List<Order> orders;
        orders = orderService.findOrderByCustomer(username);
        return orders;
    }

    public List<Order> showExecutorOrderHistory(String username) {
        List<Order> orders;
        orders = orderService.findOrderByExecutor(username);
        return orders;
    }

    public List<Order> showAvailableOrder() {
        List<Order> orders;
        orders = orderService.findOrderByStatus(OrderStatus.NEW);
        return orders;
    }


    public User uploadPhoto(File file, String username) throws ServiceException {
        userService.setUserPhoto(file, username);
        return findSingleUser(username);

    }


    public void creditAccount(String username, int sum) throws ServiceException {
        paymentLock.lock();
        User user = userService.findUserByUsername(username).get(0);
        userService.creditUser(user, sum);
        paymentLock.unlock();
    }

    public void addNotify(String text, String username) throws ServiceException {
        User user = userService.findUserByUsername(username).get(0);
        notifyService.addNotify(text, user,NotifyType.SIMPLE_TEXT);
    }

    public void addDocument(File file, String username) throws ServiceException {
        User user = findSingleUser(username);
        documentService.addDocument(user, file);

    }


}
