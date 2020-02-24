package by.victor.beta.service.util;

import by.victor.beta.entity.Entity;
import by.victor.beta.entity.NotifyType;
import by.victor.beta.entity.Order;
import by.victor.beta.entity.User;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NotifyMessageBuilder {
    private static final Logger logger= LogManager.getLogger(NotifyMessageBuilder.class);
    private static final String NOTIFY_TEXT_BUNDLE_NAME = "notifyContent";
    private static final String CHAR_SEQUENCE_TO_REPLACE = "\\$\\$";
    private static final String CHAR_DELIMITER = "$";
    private static final String CHAR_DELIMITER_REGEXP = "\\$";
    private static final Locale DEFAULT_LOCALE=new Locale("be_BY");
    private static final DateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

    private String addDelimiter(List<String> strings){
        StringBuilder result = new StringBuilder();
        strings.forEach(str->{
            result.append(str);
            result.append(CHAR_DELIMITER);
        });
        logger.log(Level.TRACE,"message bones:"+result);
        return result.toString();
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

        String startDateString = SQL_DATE_FORMAT.format(order.getStartTime());
        String endDateString = SQL_DATE_FORMAT.format(order.getEndTime());
        notifyContent.add(startDateString);
        notifyContent.add(endDateString);

        return notifyContent;
    }

    private List<String> usernameAsList(User user) {
        return List.of(user.getUsername());
    }

    public String buildByPatter(List<String> strings, NotifyType type, Locale locale) {
        ResourceBundle connectionInfo = ResourceBundle.getBundle(NOTIFY_TEXT_BUNDLE_NAME, locale);
        String pattern = connectionInfo.getString(type.name());
        logger.log(Level.DEBUG,"patern: "+pattern+" values "+strings);
        for (String substring : strings) {

            logger.log(Level.DEBUG,"substr: "+substring+"  pat: "+pattern);
            pattern = pattern.replaceFirst(CHAR_SEQUENCE_TO_REPLACE, substring);

        }
        logger.log(Level.TRACE,"notification is"+pattern);
        return pattern;
    }

    public List<String> getNotifyValues(String valuesAsString){
        return Arrays.stream(valuesAsString.split(CHAR_DELIMITER_REGEXP)).collect(Collectors.toList());
    }

    public String registrationMessage(User newUser) {
        List<String> replaceStringList = List.of(newUser.getUsername(), newUser.getEmail());
        return addDelimiter(replaceStringList);
    }

    public String adminValidateMessage(User newUser, User admin) {//todo
        List<String> replaceStringList = List.of(newUser.getUsername(), newUser.getEmail(), admin.getUsername());
        return addDelimiter(replaceStringList);
    }

    public String orderNotClaimedMessage(User customer, Order order) {
        List<String> replaceStringList = new ArrayList<>(List.of(customer.getUsername()));
        replaceStringList.addAll(orderInfo(order));
        return addDelimiter(replaceStringList);
    }

    public String orderExecutionStartMessage(User customer, User executor, Order order) {
        List<String> replaceStringList = new ArrayList<>(List.of(customer.getUsername()));
        replaceStringList.addAll(orderInfo(order));
        replaceStringList.add(executor.getUsername());
        return addDelimiter(replaceStringList);
    }

    public String orderExecutionFinishToCustomer(User customer, User executor, Order order) {
        List<String> replaceStringList = new ArrayList<>(List.of(customer.getUsername()));
        replaceStringList.addAll(orderInfo(order));
        replaceStringList.add(executor.getUsername());
        return addDelimiter(replaceStringList);
    }

    public String orderExecutionFinishToExecutor(User customer, User executor, Order order) {
        List<String> replaceStringList = new ArrayList<>(List.of(executor.getUsername()));
        replaceStringList.addAll(orderInfo(order));
        replaceStringList.add(customer.getUsername());
        return addDelimiter(replaceStringList);
    }

    public String orderRefuseMessage(User customer, Order order) {
        List<String> replaceStringList = new ArrayList<>(usernameAsList(customer));
        replaceStringList.addAll(orderInfo(order));
        return addDelimiter(replaceStringList);
    }

    public String orderAcceptedMessage(User customer,User executor, Order order) {
        List<String> replaceStringList = new ArrayList<>(usernameAsList(customer));
        replaceStringList.addAll(orderInfo(order));
        replaceStringList.add(executor.getUsername());//
        return addDelimiter(replaceStringList);
    }

    public String orderCanceledMessageToExecutor(User executor) {
        List<String> replaceStringList = new ArrayList<>(usernameAsList(executor));
        return addDelimiter(replaceStringList);
    }

    public String orderCanceledMessageToCustomer(User executor,Order order) {
        List<String> replaceStringList = new ArrayList<>(usernameAsList(executor));
        replaceStringList.addAll(orderInfo(order));
        return addDelimiter(replaceStringList);
    }

    Function<Entity[],List<String>> orderCanceledToCustomer=(entities)->{//todo ooo
        User customer=(User) entities[0];
        Order order=(Order) entities[1];
       return null;
    };

    public String orderCreateMessage(User customer, Order order) {
        List<String> replaceStringList = new ArrayList<>(usernameAsList(customer));
        replaceStringList.addAll(orderInfo(order));
        return addDelimiter(replaceStringList);
    }

    public String buildByPatter(List<String> notifyValues, NotifyType type) {
        return buildByPatter(notifyValues,type, DEFAULT_LOCALE);
    }

    public String buildEmailVerification(User user,UUID uuid){
        return buildByPatter( List.of(user.getUsername(),uuid.toString()), NotifyType.REGISTRATION_EMAIL_VERIFY_REQUEST);
    }
}
