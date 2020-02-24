package by.victor.beta.command.impl.order;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

public class AcceptOrderCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router=new Router(PagePath.ACCEPT_ORDER_RESULT);;
        int orderId= Integer.parseInt( (String) content.getRequestParameter(AttributeName.ORDER));
        String username=(String)  content.getSessionAttribute(AttributeName.USERNAME);
        try {
            ServiceFacade.INSTANCE.acceptOrder(orderId,username);
            content.setRequestAttribute(AttributeName.COMMAND_RESULT, PageContentKey.SUCCESSFULLY);
        } catch (ServiceException ex) {
            content.setRequestAttribute(AttributeName.COMMAND_RESULT, PageContentKey.FAILED);

        }

        return router;
    }
}
