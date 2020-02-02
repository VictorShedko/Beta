package by.victor.beta.command.impl.user;

import by.victor.beta.command.AttributeNameProvider;
import by.victor.beta.command.PagePathProvider;
import by.victor.beta.command.AbstractCommand;
import by.victor.beta.command.CommandException;
import by.victor.beta.command.Router;
import by.victor.beta.command.RequestSessionContent;
import by.victor.beta.entity.Role;
import by.victor.beta.entity.User;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import java.util.Optional;


public class RegistrationCommand implements AbstractCommand {

    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router;
        String username=(String)content.getRequestParameter(AttributeNameProvider.USERNAME);
        String password=(String)content.getRequestParameter(AttributeNameProvider.PASSWORD);
        Role role=Role.fromValue((String) content.getRequestParameter(AttributeNameProvider.ROLE));
        String login=(String)content.getRequestParameter(AttributeNameProvider.LOGIN);
        String email=(String)content.getRequestParameter(AttributeNameProvider.EMAIL);
        User user;
        try {
            user = ServiceFacade.instance.registerUser(username,password,login,role,email);
            switch (role) {
                case ADMIN:
                case CUSTOMER:
                case EXECUTOR:
                    router = new Router(PagePathProvider.USER_MAIN_PAGE);
                    break;
                case DEFAULT: {
                    router = new Router(PagePathProvider.LOGIN_PAGE);
                    content.setRequestAttribute("loginErrorMessage","Не верный логин или пароль");
                    break;
                }
                default:
                    throw new CommandException();
            }
            content.setSessionAttribute(AttributeNameProvider.USERNAME,user.getUsername());
            content.setSessionAttribute(AttributeNameProvider.ROLE,user.getRole());
            content.setSessionAttribute(AttributeNameProvider.BALANCE,user.getBalance());
            if(user.getPhotoPath()!=null) {//todo optional
                content.setSessionAttribute(AttributeNameProvider.PHOTO_PATH, user.getPhotoPath());
            }else {
                content.setSessionAttribute(AttributeNameProvider.PHOTO_PATH,
                        AttributeNameProvider.DEFAULT_PHOTO_PATH );
            }
        } catch (ServiceException e) {
            content.setSessionAttribute(AttributeNameProvider.ERROR_MESSAGE_HEADER,e.getMessage());
            router=new Router(PagePathProvider.REGISTRATION_PAGE);

        }
        return router;
    }
}
