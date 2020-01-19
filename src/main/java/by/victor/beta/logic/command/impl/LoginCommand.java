package by.victor.beta.logic.command.impl;

import by.victor.beta.logic.command.AttributeNameProvider;
import by.victor.beta.logic.command.PagePathProvider;
import by.victor.beta.logic.command.AbstractCommand;
import by.victor.beta.logic.command.CommandException;
import by.victor.beta.logic.command.Router;
import by.victor.beta.logic.command.RequestSessionContent;
import by.victor.beta.logic.entity.User;
import by.victor.beta.logic.service.ServiceFacade;

import java.util.Optional;


public class LoginCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router;
        String login = (String) content.getRequestParameter(AttributeNameProvider.LOGIN);
        String password = (String) content.getRequestParameter(AttributeNameProvider.PASSWORD);

        Optional<User> userOptional = ServiceFacade.instance.login(login, password);//todo сомнительно
        if (userOptional.isPresent()) {
            User user=userOptional.get();
            switch (user.getRole()) {
                case ADMIN: {
                    router = new Router(PagePathProvider.ADMIN_MAIN_PAGE);
                    content.setSessionAttribute(AttributeNameProvider.USERNAME, user.getUsername());
                    content.setSessionAttribute(AttributeNameProvider.ROLE, user.getRole());
                    break;
                }
                case USER: {
                    router = new Router(PagePathProvider.CUSTOMER_MAIN_PAGE);
                    content.setSessionAttribute(AttributeNameProvider.USERNAME,user.getUsername());
                    content.setSessionAttribute(AttributeNameProvider.ROLE,user.getRole());
                    break;
                }
                case EXECUTOR: {
                    router = new Router(PagePathProvider.EXECUTOR_MAIN_PAGE);
                    content.setSessionAttribute(AttributeNameProvider.USERNAME,user.getUsername());
                    content.setSessionAttribute(AttributeNameProvider.ROLE,user.getRole());
                    break;
                }
                case DEFAULT: {
                    router = new Router(PagePathProvider.LOGIN_PAGE);
                    content.setRequestAttribute("loginErrorMessage", "Не верный логин или пароль");
                    break;
                }
                default:
                    throw new CommandException();
            }
        }else {
            router = new Router(PagePathProvider.LOGIN_PAGE);
            content.setRequestAttribute("loginErrorMessage", "Повторите попытку позже");
        }
        return router;
    }
}

