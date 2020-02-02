package by.victor.beta.service.notifyservice;

import by.victor.beta.entity.NotifyType;
import by.victor.beta.entity.Order;
import by.victor.beta.entity.User;

import java.util.*;
import java.util.stream.Collectors;

public class NotifyMessageBuilder {
    private static final String NOTIFY_TEXT_BUNDLE_NAME = "notifyContent";
    private static final String CHAR_SEQUENCE_TO_REPLACE = "\\$\\$";
    private static final String CHAR_DELIMITER = "\\$";
    public String buildByPatter(List<String> strings, NotifyType type, Locale locale) {
        ResourceBundle connectionInfo = ResourceBundle.getBundle(NOTIFY_TEXT_BUNDLE_NAME, locale);
        String pattern = connectionInfo.getString(type.name());
        for (String substring : strings) {

                pattern = pattern.replaceFirst(CHAR_SEQUENCE_TO_REPLACE, substring);

        }
        return pattern;
    }

    private String addDelimiter(List<String> strings){
        StringBuilder result = new StringBuilder();
        strings.forEach(str->{
            result.append(str);
            result.append(CHAR_DELIMITER);
        });
        return result.toString();
    }

    public List<String> getNotifyValues(String valuesAsString){
        return Arrays.stream(valuesAsString.split(CHAR_DELIMITER)).collect(Collectors.toList());
    }

    private List<String> orderInfo(Order order) {
        List<String> notifyContent = new ArrayList<>();
        notifyContent.add(order.getAddress());

        if (order.getDescription() != null) {
            notifyContent.add(order.getDescription());
        }else {
            notifyContent.add("");
        }
        notifyContent.add(Integer.toString(order.getPrice()));
        if (order.getExecutor() != null) {
            notifyContent.add(order.getExecutor());
        }

        return notifyContent;
    }

    private List<String> usernameAsList(User user) {
        return List.of(user.getUsername());
    }

    private List<String> userInfo(User user) {
        List<String> notifyContent = new ArrayList<>();
        notifyContent.add(user.getUsername());
        notifyContent.add(user.getEmail());
        return notifyContent;
    }

    public String buildValuesRegistrationMessage(User newUser) {
        List<String> replaceStringList = List.of(newUser.getUsername(), newUser.getEmail());
        return addDelimiter(replaceStringList);
    }

    public String buildValuesVerifiedMessage(User newUser) {
        List<String> replaceStringList = List.of(newUser.getUsername(), newUser.getEmail());
        return addDelimiter(replaceStringList);
    }

    public String adminValuesValidateMessageMessage(User newUser, User admin) {
        List<String> replaceStringList = List.of(newUser.getUsername(), newUser.getEmail(), admin.getUsername());
        return addDelimiter(replaceStringList);
    }

    public String orderValuesNotClaimedMessage(User customer, Order order) {
        List<String> replaceStringList = new ArrayList<>(List.of(customer.getUsername()));
        replaceStringList.addAll(orderInfo(order));
        return addDelimiter(replaceStringList);
    }

    public String orderExecutionStartMessage(User customer, User newUser, Order order) {//todo
        List<String> replaceStringList = List.of(newUser.getUsername(), newUser.getEmail());
        return addDelimiter(replaceStringList);
    }

    public String orderExecutionFinishMessage(User customer, User newUser, Order order) {//todo
        List<String> replaceStringList = List.of(newUser.getUsername(), newUser.getEmail());
        return addDelimiter(replaceStringList);
    }

    public String orderRefuseMessage(User customer, Order order) {
        List<String> replaceStringList = new ArrayList<>(usernameAsList(customer));
        replaceStringList.addAll(orderInfo(order));
        return addDelimiter(replaceStringList);
    }

    public String orderAcceptedMessage(User newUser) {//todo
        List<String> replaceStringList = List.of(newUser.getUsername(), newUser.getEmail());
        return addDelimiter(replaceStringList);
    }

    public String orderCanceledMessageToExecutor(User newUser) {//todo
        List<String> replaceStringList = List.of(newUser.getUsername(), newUser.getEmail());
        return addDelimiter(replaceStringList);
    }

    public String orderCanceledMessageToCustomer(User newUser) {//todo
        List<String> replaceStringList = List.of(newUser.getUsername(), newUser.getEmail());
        return addDelimiter(replaceStringList);
    }

    public String orderCreateMessage(User newUser, Order order) {//todo
        List<String> replaceStringList = List.of(newUser.getUsername(), newUser.getEmail());
        return addDelimiter(replaceStringList);
    }


}
