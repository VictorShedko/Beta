package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.entity.User;
import by.victor.beta.entity.UserStatus;
import by.victor.beta.service.ServiceFacade;

import java.util.List;

public class ShowUserByStatusCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        UserStatus status=UserStatus.valueOf((String)content.getRequestParameter(AttributeName.STATUS));
        List<User> userList= ServiceFacade.INSTANCE.showUserByStatus(status);
        content.setRequestAttribute(AttributeName.USER_LIST,userList);
        content.setRequestAttribute(AttributeName.USER_SEARCH_PARAMETER,PageContentKey.STATUS);
        content.setRequestAttribute(AttributeName.USER_SEARCH_PARAMETER_VALUE,status.toString());
        return new Router(PagePath.USER_LIST);
    }
}
