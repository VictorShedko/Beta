package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.entity.User;
import by.victor.beta.entity.UserStatus;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import java.util.List;
import java.util.Optional;

public class ShowUserProfile implements AbstractCommand{

    public Router execute(RequestSessionContent content) throws CommandException {
        String username=(String)content.getRequestParameter(AttributeNameProvider.SEARCH_NAME);
        User user= null;
        try {
            user = ServiceFacade.instance.findUser( username);
        } catch (ServiceException e) {
            throw new CommandException();
        }
        content.setRequestAttribute(AttributeNameProvider.USER_INFO,user);

        UserStatus status=UserStatus.valueOf((String)content.getRequestParameter(AttributeNameProvider.STATUS));
        List<User> userList= ServiceFacade.instance.showUserByStatus(status);
        content.setRequestAttribute(AttributeNameProvider.AVAILABLE_ORDER,userList);
        return new Router(PagePathProvider.USER_PROFILE);
    }
}

