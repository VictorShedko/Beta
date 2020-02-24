package by.victor.beta.command.impl.order;

import by.victor.beta.command.*;
import by.victor.beta.entity.Order;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import java.util.List;

public class ShowAvailableOrdersCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        List<Order> orderList= null;
        try {
            orderList = ServiceFacade.INSTANCE.showAvailableOrder();
        } catch (ServiceException e) {
            throw new CommandException();
        }
        content.setRequestAttribute(AttributeName.AVAILABLE_ORDER,orderList);
        return new Router(PagePath.AVAILABLE_ORDERS);
    }
}
