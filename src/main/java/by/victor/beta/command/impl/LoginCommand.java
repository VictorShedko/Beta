package by.victor.beta.command.impl;

import by.victor.beta.command.AttributeNameProvider;
import by.victor.beta.command.PagePathProvider;
import by.victor.beta.command.AbstractCommand;
import by.victor.beta.command.CommandException;
import by.victor.beta.command.Router;
import by.victor.beta.command.RequestSessionContent;
import by.victor.beta.entity.User;
import by.victor.beta.service.ServiceFacade;

import java.util.Optional;


public class LoginCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router;
        String login = (String) content.getRequestParameter(AttributeNameProvider.LOGIN);
        String password = (String) content.getRequestParameter(AttributeNameProvider.PASSWORD);
        Optional<User> userOptional = ServiceFacade.instance.login(login, password);
        if (userOptional.isPresent()) {
            User user=userOptional.get();
            content.setRequestAttribute("lol","d,");
            switch (user.getRole()) {
                case ADMIN:
                case CUSTOMER:
                case EXECUTOR: {
                    router = new Router(PagePathProvider.USER_MAIN_PAGE);
                    content.setSessionAttribute(AttributeNameProvider.USERNAME,user.getUsername());
                    content.setSessionAttribute(AttributeNameProvider.ROLE,user.getRole());
                    content.setSessionAttribute(AttributeNameProvider.BALANCE,user.getBalance());
                    content.setSessionAttribute(AttributeNameProvider.STATUS,user.getStatus());
                    if(user.getPhotoPath()!=null) {//todo optional
                        content.setSessionAttribute(AttributeNameProvider.PHOTO_PATH, user.getPhotoPath());
                    }else {
                        content.setSessionAttribute(AttributeNameProvider.PHOTO_PATH,
                                AttributeNameProvider.DEFAULT_PHOTO_PATH );
                    }
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
            content.setRequestAttribute(AttributeNameProvider.FEEDBACK, "неверное имя пользователя или паролб");
        }

        return router;
    }
}

