package by.victor.beta.command.impl.order;

import by.victor.beta.command.*;
import by.victor.beta.entity.Order;
import by.victor.beta.service.ServiceFacade;

import java.util.ArrayList;
import java.util.List;

public class ShowExecutorOrderHistory implements AbstractCommand {

    @Override
    public Router execute(RequestSessionContent content) throws CommandException {

        String username = (String) content.getSessionAttribute(AttributeNameProvider.USERNAME);
        List<Order> orderList= ServiceFacade.instance.showExecutorOrderHistory(username);
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
        content.setRequestAttribute(AttributeNameProvider.ACTIVE_ORDERS_LIST,activeOrders);
        content.setRequestAttribute(AttributeNameProvider.COMPETED_ORDERS_LIST,completedOrders);
        content.setRequestAttribute(AttributeNameProvider.IN_PROGRESS_ORDERS_LIST,workInProgressOrders);
        return new Router(PagePathProvider.EXECUTOR_ORDER_HISTORY);
    }
}
