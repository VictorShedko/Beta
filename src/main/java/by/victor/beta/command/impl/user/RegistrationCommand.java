package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.entity.util.Role;
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
                path =prgParameterManager.addParameter(PagePath.PRG_TO_USER_MENU,AttributeName.FEEDBACK,
                        PageContentKey.SUCCESSFULLY);
            } else {
                path =prgParameterManager.addParameter(PagePath.PRG_TO_REGISTRATION,AttributeName.FEEDBACK,
                        validator.getInvalidFeedback());
            }
        } catch (ServiceException e) {
            path=prgParameterManager.addParameter(PagePath.PRG_TO_REGISTRATION,AttributeName.ERROR_MESSAGE_HEADER,
                    e.getMessage());
        }
        router=new Router(path);
        router.setRedirect();
        return router;
    }
}
