package by.victor.beta.command.impl.order;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

public class CancelOrderCustomerCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router=new Router(PagePathProvider.USER_MAIN_PAGE);;
        int orderId=Integer.parseInt((String) content.getRequestParameter(AttributeNameProvider.ORDER));
        try {
            ServiceFacade.instance.cancelOrderByCustomer(orderId);
            content.setRequestAttribute(AttributeNameProvider.COMMAND_RESULT,"прошло хорошо");//todo в константы
        }catch (ServiceException ex){
            new Router(PagePathProvider.USER_MAIN_PAGE);
        }
        return router;
    }
}
