package by.victor.beta.command.impl.order;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

public class RefuseOrderExecutorCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {

        Router router = new Router(PagePath.USER_MAIN_MENU);
        int orderId = Integer.parseInt((String) content.getRequestParameter(AttributeName.ORDER));
        try {
            ServiceFacade.instance.refuseOrderByExecutor(orderId);
            content.setRequestAttribute(AttributeName.COMMAND_RESULT, "прошло хорошо");
        } catch (ServiceException ex) {
            new Router(PagePath.USER_MAIN_MENU);
        }
        return router;


    }
}
