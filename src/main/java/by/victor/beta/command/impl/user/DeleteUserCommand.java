package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

public class DeleteUserCommand implements AbstractCommand{
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router=new Router(PagePathProvider.CREATE_ORDER_RESULT);;
        String username=(String)  content.getSessionAttribute(AttributeNameProvider.USERNAME);
        try {
            ServiceFacade.instance.deleteUser(username);
            content.setRequestAttribute("acceptOrderResult","успешно ");//todo в константы

        } catch (ServiceException ex) {
            ex.printStackTrace();//todo
            content.setRequestAttribute("acceptOrderResult","уже занято");

        }

        return router;
    }
}
