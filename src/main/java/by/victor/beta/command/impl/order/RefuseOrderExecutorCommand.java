package by.victor.beta.command.impl.order;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

public class RefuseOrderExecutorCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {

        Router router = new Router(PagePath.USER_MAIN_MENU);
        String username = (String) content.getSessionAttribute(AttributeName.USERNAME);
        int orderId = Integer.parseInt((String) content.getRequestParameter(AttributeName.ORDER));
        try {
            if(ServiceFacade.INSTANCE.refuseOrderByExecutor(orderId,username)){
                content.setRequestAttribute(AttributeName.COMMAND_RESULT, PageContentKey.SUCCESSFULLY);
            }else {
                content.setRequestAttribute(AttributeName.COMMAND_RESULT, PageContentKey.FAILED);
            }

        } catch (ServiceException ex) {
            new Router(PagePath.USER_MAIN_MENU);
        }
        return router;


    }
}
