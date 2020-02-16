package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

public class DeleteUserCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router=new Router(PagePath.CREATE_ORDER_RESULT);;
        String username=(String)  content.getSessionAttribute(AttributeName.USERNAME);
        try {
            ServiceFacade.instance.deleteUser(username);
            content.setRequestAttribute(AttributeName.COMMAND_RESULT, PageContentKey.SUCCESSFULLY);
        } catch (ServiceException ex) {
            content.setRequestAttribute(AttributeName.COMMAND_RESULT, PageContentKey.FAILED);
        }

        return router;
    }
}
