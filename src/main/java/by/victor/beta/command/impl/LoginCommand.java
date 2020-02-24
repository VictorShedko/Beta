package by.victor.beta.command.impl;

import by.victor.beta.command.*;
import by.victor.beta.entity.User;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import java.util.Optional;


public class LoginCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router;
        String login = (String) content.getRequestParameter(AttributeName.LOGIN);
        String password = (String) content.getRequestParameter(AttributeName.PASSWORD);
        Optional<User> userOptional ;
        try {
            userOptional = ServiceFacade.INSTANCE.login(login, password);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (userOptional.isPresent()) {
            User user=userOptional.get();
            content.addUserToSession(user);
            content.setSessionAttribute(AttributeName.FEEDBACK, PageContentKey.EMPTY);
            router=new Router(PagePath.PRG_TO_USER_MENU);
            router.setRedirect();
        }else {
            router = new Router(PagePath.INDEX);
            content.setSessionAttribute(AttributeName.FEEDBACK, PageContentKey.INVALID_LOGIN_OR_PASSWORD);
            router.setRedirect();
        }

        return router;
    }
}

