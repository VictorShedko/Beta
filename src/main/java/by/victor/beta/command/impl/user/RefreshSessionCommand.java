package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.entity.User;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

public class RefreshSessionCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router;
        String username=(String)content.getSessionAttribute(AttributeName.USERNAME);

        User user;
        try {
                user = ServiceFacade.INSTANCE.findUserByUsername(username);
                content.addUserToSession(user);
            router=new Router(PagePath.USER_MAIN_MENU);
        } catch (ServiceException e) {
            router=new Router(PagePath.ERROR);

        }
        return router;
    }
}
