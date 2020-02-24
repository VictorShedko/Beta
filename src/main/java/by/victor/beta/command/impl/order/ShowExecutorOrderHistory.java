package by.victor.beta.command.impl.order;

import by.victor.beta.command.*;
import by.victor.beta.entity.Order;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import java.util.ArrayList;
import java.util.List;

public class ShowExecutorOrderHistory implements Command {

    @Override
    public Router execute(RequestSessionContent content) throws CommandException {

        String username = (String) content.getSessionAttribute(AttributeName.USERNAME);
        List<Order> orderList;
        try {
            orderList = ServiceFacade.INSTANCE.showExecutorOrderHistory(username);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        List<Order> activeOrders=new ArrayList<>();
        List<Order> completedOrders=new ArrayList<>();
        List<Order> workInProgressOrders=new ArrayList<>();
        orderList.forEach(t->{
            switch (t.getStatus()){
                case ACCEPTED:activeOrders.add(t);break;
                case IN_PROGRESS:workInProgressOrders.add(t);break;
                case COMPLETED:completedOrders.add(t);
            }
        });
        content.setRequestAttribute(AttributeName.ACTIVE_ORDERS_LIST,activeOrders);
        content.setRequestAttribute(AttributeName.COMPETED_ORDERS_LIST,completedOrders);
        content.setRequestAttribute(AttributeName.IN_PROGRESS_ORDERS_LIST,workInProgressOrders);
        return new Router(PagePath.EXECUTOR_ORDER_HISTORY);
    }
}
