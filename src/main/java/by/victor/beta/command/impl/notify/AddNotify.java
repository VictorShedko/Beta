package by.victor.beta.command.impl.notify;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

@Deprecated
public class AddNotify implements Command {

    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router ;
        String username = (String) content.getSessionAttribute(AttributeName.USERNAME);
        String text=(String)content.getRequestAttribute(AttributeName.CREATE_NOTIFY_FORM_TEXT);
        try {
             ServiceFacade.INSTANCE.addNotify(username,text);
            router= new Router(PagePath.USER_MAIN_MENU);
        } catch (ServiceException ex) {
            router= new Router(PagePath.ERROR);
        }
        return router;
    }
}
