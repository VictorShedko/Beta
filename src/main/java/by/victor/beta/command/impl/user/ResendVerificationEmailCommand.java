package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.entity.User;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

public class ResendVerificationEmailCommand implements Command {


    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router;
        String username=(String)content.getSessionAttribute(AttributeName.USERNAME);
        try {
            ServiceFacade.INSTANCE.resendVerifyMail(username);
            router=new Router(PagePath.USER_MAIN_MENU);
        } catch (ServiceException e) {
            router=new Router(PagePath.ERROR);

        }
        return router;
    }
}
