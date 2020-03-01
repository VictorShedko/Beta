package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.entity.Role;
import by.victor.beta.entity.User;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;
import by.victor.beta.validator.Validator;


public class RegistrationCommand implements Command {
    private static PRGParameterManager prgParameterManager=new PRGParameterManager();
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router;
        String path;
        String username = (String) content.getRequestParameter(AttributeName.USERNAME);
        String password = (String) content.getRequestParameter(AttributeName.PASSWORD);
        Role role = Role.fromValue((String) content.getRequestParameter(AttributeName.ROLE));
        String login = (String) content.getRequestParameter(AttributeName.LOGIN);
        String email = (String) content.getRequestParameter(AttributeName.EMAIL);
        User user;
        try {
            Validator validator = new Validator();
            if (validator.isValidRegistrationForm(username, password, login,email)) {
                user = ServiceFacade.INSTANCE.registerUser(username, password, login, role, email);
                content.addUserToSession(user);
                router = new Router(PagePath.PRG_TO_USER_MENU);
            } else {
                content.setSessionAttribute(AttributeName.FEEDBACK, validator.getInvalidFeedback());
                router = new Router(PagePath.REGISTRATION_FORM);
            }
        } catch (ServiceException e) {
            path=prgParameterManager.addParameter(PagePath.REGISTRATION_FORM,AttributeName.ERROR_MESSAGE_HEADER, e.getMessage());

            router = new Router(PagePath.REGISTRATION_FORM);

        }
        return router;
    }
}
