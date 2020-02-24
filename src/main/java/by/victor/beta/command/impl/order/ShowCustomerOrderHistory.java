package by.victor.beta.command.impl.order;

import by.victor.beta.command.*;
import by.victor.beta.entity.Order;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import java.util.ArrayList;
import java.util.List;

public class ShowCustomerOrderHistory implements Command {


    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router = new Router(PagePath.CUSTOMER_ORDER_HISTORY);
        String username = (String) content.getSessionAttribute(AttributeName.USERNAME);
        List<Order> orderList = null;
        try {
            orderList = ServiceFacade.INSTANCE.showCustomerOrderHistory(username);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
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
        content.setRequestAttribute(AttributeName.ACTIVE_ORDERS_LIST, notStarted);
        content.setRequestAttribute(AttributeName.COMPETED_ORDERS_LIST, completedOrders);
        content.setRequestAttribute("lol","12");
        return router;
    }
}
