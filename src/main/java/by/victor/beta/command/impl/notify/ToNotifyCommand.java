package by.victor.beta.command.impl.notify;

import by.victor.beta.command.*;
import by.victor.beta.entity.Notification;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import java.util.List;

public class ToNotifyCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router ;
        String username = (String) content.getSessionAttribute(AttributeName.USERNAME);
        List<Notification> notifies = null;
        try {
            notifies = ServiceFacade.instance.showNotifyList(username);
            router= new Router(PagePath.NOTIFY_LIST);
        } catch (ServiceException ex) {
            router= new Router(PagePath.ERROR);
        }
        content.setRequestAttribute(AttributeName.NOTIFY_LIST, notifies);
        return router;
    }
}
