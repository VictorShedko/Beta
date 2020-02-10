package by.victor.beta.command.impl.order;

import by.victor.beta.command.*;
import by.victor.beta.entity.Order;
import by.victor.beta.service.ServiceFacade;

import java.util.ArrayList;
import java.util.List;

public class ShowCustomerOrderHistory implements AbstractCommand {


    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router = new Router(PagePathProvider.CUSTOMER_ORDER_HISTORY);
        String username = (String) content.getSessionAttribute(AttributeNameProvider.USERNAME);
        List<Order> orderList = ServiceFacade.instance.showCustomerOrderHistory(username);
        List<Order> notStarted = new ArrayList<>();
        List<Order> completedOrders = new ArrayList<>();
        orderList.forEach(t -> {
            switch (t.getStatus()) {
                case NEW:
                case ACCEPTED:
                    notStarted.add(t);
                    break;

                case IN_PROGRESS:
                case COMPLETED:
                case NOT_CLAIMED:
                    completedOrders.add(t);
            }
        });
        content.setRequestAttribute(AttributeNameProvider.ACTIVE_ORDERS_LIST, notStarted);
        content.setRequestAttribute(AttributeNameProvider.COMPETED_ORDERS_LIST, completedOrders);
        content.setRequestAttribute("lol","12");
        return router;
    }
}
