package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.entity.Role;
import by.victor.beta.entity.User;
import by.victor.beta.service.ServiceFacade;

import java.util.List;

public class ShowUserByRoleCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Role role=Role.fromValue((String)content.getRequestParameter(AttributeName.ROLE));
        List<User> userList= ServiceFacade.INSTANCE.showUserByRole(role);
        content.setRequestAttribute(AttributeName.USER_LIST,userList);
        content.setRequestAttribute(AttributeName.USER_SEARCH_PARAMETER,PageContentKey.ROLE);
        content.setRequestAttribute(AttributeName.USER_SEARCH_PARAMETER_VALUE,role.value());
        return new Router(PagePath.USER_LIST);
    }
}
