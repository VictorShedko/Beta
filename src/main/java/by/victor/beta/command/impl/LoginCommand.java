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
        Optional<User> userOptional = null;
        try {
            userOptional = ServiceFacade.instance.login(login, password);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (userOptional.isPresent()) {
            User user=userOptional.get();
            content.addUserToSession(user);
            router=new Router(PagePath.USER_MAIN_MENU);
        }else {
            router = new Router(PagePath.LOGIN);
            content.setRequestAttribute(AttributeName.FEEDBACK, PageContentKey.INVALID_LOGIN_OR_PASSWORD);
        }

        return router;
    }
}

