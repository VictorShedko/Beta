package by.victor.beta.logic.command.impl;

import by.victor.beta.logic.command.*;
import by.victor.beta.logic.entity.Order;
import by.victor.beta.logic.service.ServiceFacade;

import java.util.ArrayList;
import java.util.List;

public class ShowOrderHistoryCustomer implements AbstractCommand {


    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router =new Router(PagePathProvider.CUSTOMER_ORDER_HISTORY);
        String username = (String) content.getSessionAttribute(AttributeNameProvider.USERNAME);
        List<Order> orderList=ServiceFacade.instance.showOrderHistory(username);
        List<Order> activeOrders=new ArrayList<>();
        List<Order> completedOrders=new ArrayList<>();
        List<Order> workInProgressOrders=new ArrayList<>();
        List<Order> notClaimedOrders=new ArrayList<>();
        orderList.forEach(t->{
            switch (t.getStatus()){
                case ACCEPTED:
                case NEW:activeOrders.add(t);break;
                case IN_PROGRESS:workInProgressOrders.add(t);break;
                case COMPLETED:completedOrders.add(t);
                case NOT_CLAIMED:notClaimedOrders.add(t);
            }
        });
        content.setRequestParameter(AttributeNameProvider.ACTIVE_ORDERS_LIST,activeOrders);
        content.setRequestParameter(AttributeNameProvider.COMPETED_ORDERS_LIST,completedOrders);
        content.setRequestParameter(AttributeNameProvider.NOT_CLAIMED_ORDERS_LIST,notClaimedOrders);
        content.setRequestParameter(AttributeNameProvider.IN_PROGRESS_ORDERS_LIST,workInProgressOrders);
        return router;
    }
}
