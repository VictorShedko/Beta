package by.victor.beta.command.impl.order;

import by.victor.beta.command.*;
import by.victor.beta.entity.Order;
import by.victor.beta.service.ServiceFacade;

import java.util.List;

public class ShowAvailableOrdersCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        List<Order> orderList=ServiceFacade.instance.showAvailableOrder();
        content.setRequestAttribute(AttributeNameProvider.AVAILABLE_ORDER,orderList);
        return new Router(PagePathProvider.AVAILABLE_ORDERS);
    }
}
