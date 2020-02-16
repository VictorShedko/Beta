package by.victor.beta.command.impl.order;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

public class CancelOrderCustomerCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router=new Router(PagePath.USER_MAIN_MENU);;
        int orderId=Integer.parseInt((String) content.getRequestParameter(AttributeName.ORDER));
        try {
            ServiceFacade.instance.cancelOrderByCustomer(orderId);
            content.setRequestAttribute(AttributeName.COMMAND_RESULT, PageContentKey.SUCCESSFULLY);
        }catch (ServiceException ex){
            new Router(PagePath.USER_MAIN_MENU);
        }
        return router;
    }
}
