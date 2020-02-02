package by.victor.beta.service;

import by.victor.beta.entity.*;
import by.victor.beta.entity.UserStatus;
import by.victor.beta.repository.SqlColumnName;
import by.victor.beta.service.notifyservice.NotifyMessageBuilder;


import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CleanerFactory {
    private static final String VALUE_DELIMITER ="\\$";

    public User getUser(String username, String password, String login, Role role,String email,int balance) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setLogin(login);
        user.setRole(role);
        user.setBalance(balance);
        user.setEmail(email);
        switch (role) {
            case CUSTOMER:
            case ADMIN:
                user.setStatus(UserStatus.VERIFIED);
                break;//todo так можно?
            case EXECUTOR:
                user.setStatus(UserStatus.NEW);
                break;

        }
        return user;
    }

    public User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUsername(resultSet.getString(SqlColumnName.USERNAME));
        user.setPassword(resultSet.getString(SqlColumnName.PASSWORD));
        user.setLogin(resultSet.getString(SqlColumnName.LOGIN));
        user.setRole(Role.values()[resultSet.getInt(SqlColumnName.ROLE)]);
        user.setBalance(resultSet.getInt(SqlColumnName.BALANCE));
        user.setStatus(UserStatus.values()[resultSet.getInt(SqlColumnName.USER_STATUS)]);
        user.setId(resultSet.getLong(SqlColumnName.GLOBAL_ID));
        user.setRegistrationTime(new Date(resultSet.getTimestamp(SqlColumnName.REGISTRATION_TIME).getTime()));
        user.setPhotoPath(resultSet.getString(SqlColumnName.PHOTO_FILE_PATH));
        return user;
    }

    public Order getOrder(String customerName, String executorName, int price, Date startTime,
                          Date endTime, String address, String description,User customer) {

        Order order = new Order();
        order.setPrice(price);
        order.setDescription(description);
        order.setAddress(address);
        order.setCustomerId(customer.getId());
        order.setCustomer(customerName);
        order.setExecutor(executorName);
        order.setStartTime(startTime);
        order.setEndTime(endTime);
        order.setStatus(OrderStatus.NEW);
        return order;
    }

    public Order getOrder(ResultSet resultSet) throws SQLException {

        Order order = new Order();
        order.setOrderId(resultSet.getInt(SqlColumnName.ORDER_ID));
        order.setPrice(resultSet.getInt(SqlColumnName.PRICE));
        order.setAddress(resultSet.getString(SqlColumnName.CLEANING_ORDER_ADDRESS));
        order.setCustomer(resultSet.getString(SqlColumnName.CUSTOMER));
        order.setExecutor(resultSet.getString(SqlColumnName.EXECUTOR));
        order.setStartTime(new Date(resultSet.getTimestamp(SqlColumnName.START_TIME).getTime()));
        order.setEndTime(new Date(resultSet.getTimestamp(SqlColumnName.END_TIME).getTime()));
        order.setStatus(OrderStatus.values()[resultSet.getInt(SqlColumnName.CLEANING_ORDER_STATUS)]);
        order.setExecutorId(resultSet.getLong(SqlColumnName.EXECUTOR_ID));
        order.setCustomerId(resultSet.getLong(SqlColumnName.CUSTOMER_ID));
        return order;
    }

    public Notify getNotify(String text, User user){
        Notify notify=new Notify();
        notify.setValuesAsString(text);
        notify.setUsername(user.getUsername());
        notify.setUserId(user.getId());
        return notify;
    }



    public Notify getNotify(ResultSet resultSet) throws SQLException {
        Notify notify=new Notify();
        notify.setId(resultSet.getInt(SqlColumnName.NOTIFY_ID));
        NotifyMessageBuilder builder=new NotifyMessageBuilder();
        List<String> values=builder.getNotifyValues(resultSet.getString(SqlColumnName.NOTIFY_TEXT));
        notify.setValues(values);
        notify.setDate(new Date(resultSet.getTimestamp(SqlColumnName.NOTIFY_TIME).getTime()));
        notify.setUserId(resultSet.getLong(SqlColumnName.NOTIFY_RECEIVER_ID));
        notify.setUsername(SqlColumnName.USERNAME);
        return notify;
    }

    public Document getDocument(User user, String file){
        Document document=new Document();
        document.setUserId(user.getId());
        document.setFile(file);
        return document;
    }

    public Document getDocument(ResultSet resultSet) throws SQLException {
        Document document=new Document();
        document.setUserId(resultSet.getLong(SqlColumnName.DOCUMENT_USER_ID));
        document.setUsername(resultSet.getString(SqlColumnName.DOCUMENT_USERNAME));
        document.setAdminId(resultSet.getLong(SqlColumnName.DOCUMENT_ADMIN_ID));
        document.setAdminName(resultSet.getString(SqlColumnName.DOCUMENT_ADMIN_NAME));
        document.setFile(resultSet.getString(SqlColumnName.DOCUMENT_FILE_PATH));
        return document;
    }

    public Timestamp getSqlTimestamp(java.util.Date date) {
        return new Timestamp(date.getTime());
    }
    public Date getUtilDate(Timestamp date){
            return new Date(date.getTime());
    }
    public Date getUtilDate(ResultSet resultSet) throws SQLException {
        return  getUtilDate(resultSet.getTimestamp(SqlColumnName.TIME_FOR_TIMER));
    }

}
