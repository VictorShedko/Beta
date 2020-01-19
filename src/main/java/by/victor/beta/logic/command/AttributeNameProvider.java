package by.victor.beta.logic.command;


import java.util.List;

public class AttributeNameProvider {
    public static final String USERNAME = "username";//todo для соглосования с вебом можно брать из файла?
    public static final String ROLE = "role";
    public static final String COMMAND = "command";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    public static final String ADDRESS = "address";
    public static final String PRICE = "price";
    public static final String START_TIME = "price";
    public static final String END_TIME = "price";
    public static final String DESCRIPTION = "description";

    public static final String ACTIVE_ORDERS_LIST="newOrdersList";
    public static final String COMPETED_ORDERS_LIST ="completedOrdersList";
    public static final String NOT_CLAIMED_ORDERS_LIST="notClaimedOrdersList";
    public static final String IN_PROGRESS_ORDERS_LIST="inProgressOrdersList";



    private AttributeNameProvider() {

    }

    public static List<String> getSessionAttributeList() {
        return List.of();
    }

    public static List<String> getRequestAttributeList() {
        return List.of();
    }

    public static List<String> getRequestParameterList() {
        return List.of(PASSWORD,LOGIN);
    }
}

