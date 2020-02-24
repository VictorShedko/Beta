package by.victor.beta.command.impl.user;

import by.victor.beta.command.AttributeName;
import by.victor.beta.command.PagePath;
import by.victor.beta.command.Command;
import by.victor.beta.command.CommandException;
import by.victor.beta.command.Router;
import by.victor.beta.command.RequestSessionContent;
import by.victor.beta.entity.Role;
import by.victor.beta.entity.User;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;
import by.victor.beta.validator.Validator;


public class RegistrationCommand implements Command {

    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router;
        String username = (String) content.getRequestParameter(AttributeName.USERNAME);
        String password = (String) content.getRequestParameter(AttributeName.PASSWORD);
        Role role = Role.fromValue((String) content.getRequestParameter(AttributeName.ROLE));
        String login = (String) content.getRequestParameter(AttributeName.LOGIN);
        String email = (String) content.getRequestParameter(AttributeName.EMAIL);
        User user;
        try {
            Validator validator = new Validator();
            if (validator.isValidRegistrationForm(username, password, login)) {
                user = ServiceFacade.INSTANCE.registerUser(username, password, login, role, email);
                switch (role) {
                    case ADMIN:
                    case CUSTOMER:
                    case EXECUTOR:
                        router = new Router(PagePath.USER_MAIN_MENU);
                        break;
                    case DEFAULT: {//todo
                        router = new Router(PagePath.LOGIN);
                        content.setRequestAttribute("loginErrorMessage", "Не верный логин или пароль");
                        break;
                    }
                    default:
                        throw new CommandException();
                }
                content.addUserToSession(user);
            } else {
                content.setSessionAttribute(AttributeName.FEEDBACK, validator.getInvalidFeedback());
                router = new Router(PagePath.REGISTRATION_FORM);
            }
        } catch (ServiceException e) {
            content.setSessionAttribute(AttributeName.ERROR_MESSAGE_HEADER, e.getMessage());
            router = new Router(PagePath.REGISTRATION_FORM);

        }
        return router;
    }
}
