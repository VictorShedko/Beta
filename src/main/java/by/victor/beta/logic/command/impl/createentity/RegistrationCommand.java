package by.victor.beta.logic.command.impl.createentity;

import by.victor.beta.logic.command.AttributeNameProvider;
import by.victor.beta.logic.command.PagePathProvider;
import by.victor.beta.logic.command.AbstractCommand;
import by.victor.beta.logic.command.CommandException;
import by.victor.beta.logic.command.Router;
import by.victor.beta.logic.command.RequestSessionContent;
import by.victor.beta.logic.entity.Role;
import by.victor.beta.logic.service.ServiceFacade;


public class RegistrationCommand implements AbstractCommand {


    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        String username=(String)content.getRequestParameter(AttributeNameProvider.USERNAME);
        String password=(String)content.getRequestParameter(AttributeNameProvider.PASSWORD);
        Role role=(Role) content.getRequestParameter(AttributeNameProvider.ROLE);;
        String login=(String)content.getRequestParameter(AttributeNameProvider.LOGIN);;

        if(ServiceFacade.instance.registerUser(username,password,login,role)) {
            Router router;
            switch (role) {
                case ADMIN:
                    router = new Router(PagePathProvider.ADMIN_MAIN_PAGE);
                    break;

                case USER:
                    router = new Router(PagePathProvider.CUSTOMER_MAIN_PAGE);
                    break;

                case EXECUTOR:
                    router = new Router(PagePathProvider.EXECUTOR_MAIN_PAGE);
                    break;

                case DEFAULT: {
                    router = new Router(PagePathProvider.LOGIN_PAGE);
                    content.setRequestAttribute("loginErrorMessage","Не верный логин или пароль");
                    break;
                }
                default:
                    throw new CommandException();
            }
            return router;
        }else {
            return new Router(PagePathProvider.ERROR_PAGE);
        }
    }
}
