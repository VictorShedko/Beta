package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.entity.Role;
import by.victor.beta.entity.User;
import by.victor.beta.service.ServiceFacade;

import java.util.List;

public class ShowAllUserCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        List<User> userList= ServiceFacade.INSTANCE.showAllUserByRole();
        content.setRequestAttribute(AttributeName.USER_LIST,userList);
        content.setRequestAttribute(AttributeName.USER_SEARCH_PARAMETER,PageContentKey.ALL);

        return new Router(PagePath.USER_LIST);
    }
}
