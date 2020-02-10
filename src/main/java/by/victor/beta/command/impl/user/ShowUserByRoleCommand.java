package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.entity.Role;
import by.victor.beta.entity.User;
import by.victor.beta.entity.UserStatus;
import by.victor.beta.service.ServiceFacade;

import java.util.List;

public class ShowUserByRoleCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Role role=Role.fromValue((String)content.getRequestParameter(AttributeNameProvider.ROLE));
        List<User> userList= ServiceFacade.instance.showUserByRole(role);
        content.setRequestAttribute(AttributeNameProvider.USER_LIST,userList);
        content.setRequestAttribute(AttributeNameProvider.USER_SEARCH_PARAMETER,"Role");//todo
        content.setRequestAttribute(AttributeNameProvider.USER_SEARCH_PARAMETER_VALUE,role.value());
        return new Router(PagePathProvider.USER_LIST);
    }
}
