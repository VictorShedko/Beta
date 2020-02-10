package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.entity.User;
import by.victor.beta.entity.UserStatus;
import by.victor.beta.service.ServiceFacade;

import java.util.List;

public class ShowUserByStatusCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        UserStatus status=UserStatus.valueOf((String)content.getRequestParameter(AttributeNameProvider.STATUS));
        List<User> userList= ServiceFacade.instance.showUserByStatus(status);
        content.setRequestAttribute(AttributeNameProvider.USER_LIST,userList);
        content.setRequestAttribute(AttributeNameProvider.USER_SEARCH_PARAMETER,"Status");//todo
        content.setRequestAttribute(AttributeNameProvider.USER_SEARCH_PARAMETER_VALUE,status.toString());
        return new Router(PagePathProvider.USER_LIST);
    }
}
