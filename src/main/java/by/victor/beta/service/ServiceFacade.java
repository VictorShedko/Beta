package by.victor.beta.service;

import by.victor.beta.entity.*;
import by.victor.beta.entity.util.UserStatus;
import by.victor.beta.entity.util.NotifyType;
import by.victor.beta.entity.util.OrderStatus;
import by.victor.beta.entity.util.Role;
import by.victor.beta.service.impl.*;
import by.victor.beta.service.util.mail.MailServiceThread;
import by.victor.beta.service.util.HashService;
import by.victor.beta.service.util.NotifyMessageBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public enum ServiceFacade {
    INSTANCE;
    private static final Logger logger = LogManager.getLogger(ServiceFacade.class);
    private final IUserService userService = new UserService();
    private final INotifyService notifyService = new NotifyService();
    private final IDocumentService documentService = new DocumentService();
    private final NotifyMessageBuilder notifyMessageBuilder = new NotifyMessageBuilder();
    private final IOrderService orderService = new OrderService(notifyMessageBuilder, userService, notifyService);
    private final IVerifyCodeService verifyCodeService = new VerifyCodeService();
    private final CleanerEntityProvider entityProvider = new CleanerEntityProvider();

    private static final ReentrantLock paymentLock = new ReentrantLock();
    public static final long ONE_HOUR = TimeUnit.HOURS.toMillis(1);

    private Order findSingleOrderById(int id) throws ServiceException {
        List<Order> orders = orderService.findOrderById(id);
        if (orders.size() == 1) {
            return orders.get(0);
        } else {
            throw new ServiceException("order not found");
        }
    }

    private User findSingleUser(String username, boolean deleteSensitive) throws ServiceException {

        List<User> users = userService.findUserByUsername(username);
        if (users.size() == 1 &&
                (!deleteSensitive || users.get(0).getStatus() != UserStatus.DELETED)) {
            return users.get(0);
        } else {
            throw new ServiceException("ser not found");
        }
    }

    public User findUserByUsername(String username,boolean deleteSensitive) throws ServiceException {
        User user = findSingleUser(username,deleteSensitive);
        return user;
    }

    private void sendVerificationMessage(User user) throws ServiceException {
        UUID uuid = UUID.randomUUID();
        VerifyCode verifyCode = entityProvider.getToken(user.getId(), uuid.toString());
        verifyCodeService.addToken(verifyCode);
        String emailText = notifyMessageBuilder.buildEmailVerification(user, uuid);
        MailServiceThread.sendMessage(user, emailText);
    }

    public void resendVerifyMail(String username) throws ServiceException {
        sendVerificationMessage(findSingleUser(username,true));
    }

    public boolean emailVerify(String uuid) throws ServiceException {
        Optional<VerifyCode> verifyCode = verifyCodeService.getSingleTokenByUuid(uuid);
        if (verifyCode.isPresent()) {
            User user = findSingleUser(verifyCode.get().getUsername(),true);
            if (verifyCodeService.isValidToken(verifyCode.get(), user)) {
                userService.emailVerify(user);
            }
            return findSingleUser(user.getUsername(),true).getStatus() != UserStatus.NEW;
        } else {
            return false;
        }
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        List<User> users = userService.findUserByLogin(login);

        if (users.size() == 1 && HashService.INSTANCE.isRightPassword(users.get(0), password) &&
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
            User user = entityProvider.getUser(username, password, login, role, email, 0);
            userService.addUser(user);
            user = findSingleUser(username,true);
            sendVerificationMessage(user);
            String notifyText = notifyMessageBuilder.registrationMessage(user);
            notifyService.addNotify(notifyText, user, NotifyType.REGISTRATION);
            return user;
        } else {
            throw new ServiceException();
        }

    }

    public void deleteUser(String username) throws ServiceException {
        paymentLock.lock();
        try {
            userService.setUserStatus(UserStatus.DELETED, username);
        }finally {
            paymentLock.unlock();
        }

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

    public boolean createOrder(String address, String description, String username,
                               Date startTime, Date endTime, int price) throws ServiceException {

        try {
            paymentLock.lock();
            User user = findSingleUser(username,true);
            if (userService.debitUser(user, price)) {
                logger.log(Level.TRACE, "debit user " + price);
                Order order = entityProvider.getOrder(username, null, price, startTime, endTime,
                        address, description, user);
                orderService.addOrder(order);
                logger.log(Level.TRACE, "added user " + order);
                String notifyText = notifyMessageBuilder.orderCreateMessage(user, order);
                notifyService.addNotify(notifyText, user, NotifyType.ORDER_CREATED);
                return true;
            } else {
                logger.log(Level.TRACE, "debit user fail " + price);
                return false;
            }
        } finally {
            paymentLock.unlock();
        }
    }

    public boolean acceptOrder(int orderId, String executorName) throws ServiceException {
        try {
            paymentLock.lock();

            Order order = findSingleOrderById(orderId);
            User executor = findSingleUser(executorName,true);
            orderService.acceptOrder(order, executor);
            NotifyMessageBuilder builder = new NotifyMessageBuilder();
            User customer = findSingleUser(order.getCustomer(),false);
            String text = builder.orderAcceptedMessage(customer, executor, order);
            NotifyType type = NotifyType.ORDER_ACCEPTED;
            notifyService.addNotify(text, customer, type);
            return true;
        } finally {
            paymentLock.unlock();
        }
    }

    public boolean cancelOrderByCustomer(int orderId) throws ServiceException {
        try {
            paymentLock.lock();

            Order order = findSingleOrderById(orderId);
            if (orderService.updateOrderStatus(order, OrderStatus.CANCELED, ONE_HOUR)) {
                User customer = findSingleUser(order.getCustomer(),true);
                userService.creditUser(customer, order.getPrice());
                String customerNotifyText = notifyMessageBuilder.orderCanceledMessageToCustomer(customer, order);
                notifyService.addNotify(customerNotifyText, customer, NotifyType.ORDER_CANCEL_TO_CUSTOMER);
                if (order.getExecutor() != null) {
                    User executor = findSingleUser(order.getExecutor(),true);
                    String executorNotifyText = notifyMessageBuilder.orderCanceledMessageToExecutor(executor,order);
                    notifyService.addNotify(executorNotifyText, executor, NotifyType.ORDER_CANCEL_TO_EXECUTOR);
                }
                return true;
            } else {
                return false;
            }
        } finally {
            paymentLock.unlock();
        }
    }

    public boolean refuseOrderByExecutor(int orderId,String username) throws ServiceException {
        Order order = findSingleOrderById(orderId);
        User executor=findSingleUser(username,true);
        if (order.getExecutorId()==executor.getId()&&orderService.updateOrderStatus(order, OrderStatus.NEW, ONE_HOUR)) {
            User customer = findSingleUser(order.getCustomer(),true);
            String notifyText = notifyMessageBuilder.orderRefuseMessage(customer, order);
            notifyService.addNotify(notifyText, customer, NotifyType.ORDER_EXECUTOR_REFUSE);
            return true;
        } else {
            return false;
        }

    }

    public List<Document> showUserDocument(String username) throws ServiceException {
        return documentService.getUserDocuments(username);
    }

    public void checkDocument(int id, String adminName) throws ServiceException {
        User admin = findSingleUser(adminName,true);
        Document document = documentService.findDocumentById(id);
        documentService.checkDocument(admin, document);
    }

    public List<Notification> showNotifyList(String username) throws ServiceException {
        List<Notification> notifies;
        notifies = notifyService.findNotifies(username);
        return notifies;
    }

    public List<Order> showCustomerOrderHistory(String username) throws ServiceException {
        List<Order> orders;
        orders = orderService.findOrderByCustomer(username);
        return orders;
    }

    public List<Order> showExecutorOrderHistory(String username) throws ServiceException {
        List<Order> orders;
        orders = orderService.findOrderByExecutor(username);
        return orders;
    }

    public List<Order> showAvailableOrder() throws ServiceException {
        List<Order> orders;
        orders = orderService.findOrderByStatus(OrderStatus.NEW);
        return orders;
    }


    public User uploadPhoto(File file, String username) throws ServiceException {
        if (file==null){
            throw new ServiceException();
        }
        userService.setUserPhoto(file, username);
        return findSingleUser(username,true);

    }

    public void creditAccount(String username, int sum) throws ServiceException {
        paymentLock.lock();
        User user = userService.findUserByUsername(username).get(0);
        userService.creditUser(user, sum);
        paymentLock.unlock();
    }

    public void addDocument(File file, String username) throws ServiceException {
        if(file==null){
            throw new ServiceException();
        }
        User user = findSingleUser(username,true);
        documentService.addDocument(user, file);
    }

    public List<User> showAllUserByRole() {
        return userService.findAll();
    }

    public List<Document> showUserDocuments(String username) throws ServiceException {
        return documentService.getUserDocuments(username);
    }

    public void initOrderAutoUpdate() {
        OrderUpdateManager.INSTANCE.update(orderService);
    }
}
