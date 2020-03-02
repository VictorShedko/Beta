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
            router=new Router(PagePath.PRG_TO_USER_MENU);
            router.setRedirect();
        }else {
            PRGParameterManager manager=new PRGParameterManager();
            String path=manager.addParameter(PagePath.PRG_TO_LOGIN,AttributeName.FEEDBACK,PageContentKey.INVALID_LOGIN_OR_PASSWORD);
            router = new Router(path);
            router.setRedirect();
        }

        return router;
    }
}

