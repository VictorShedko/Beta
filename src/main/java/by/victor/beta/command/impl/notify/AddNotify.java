package by.victor.beta.command.impl.notify;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

public class AddNotify implements AbstractCommand {

    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router ;
        String username = (String) content.getSessionAttribute(AttributeNameProvider.USERNAME);
        String text=(String)content.getRequestAttribute(AttributeNameProvider.CREATE_NOTIFY_FORM_TEXT);
        try {
             ServiceFacade.instance.addNotify(username,text);
            router= new Router(PagePathProvider.USER_MAIN_PAGE);
        } catch (ServiceException ex) {
            router= new Router(PagePathProvider.ERROR_PAGE);
        }
        return router;
    }
}
