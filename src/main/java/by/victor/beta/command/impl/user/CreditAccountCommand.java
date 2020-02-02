package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.entity.Order;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

public class CreditAccountCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router=new Router(PagePathProvider.CREATE_ORDER_RESULT);;
        String username=(String)  content.getSessionAttribute(AttributeNameProvider.USERNAME);
        Integer sum=Integer.parseInt((String) content.getRequestParameter(AttributeNameProvider.CREDIT_SUM));
        try {
            ServiceFacade.instance.creditAccount(username, sum);
            content.setRequestAttribute("acceptOrderResult","успешно ");//todo в константы

        } catch (ServiceException repositoryException) {
            repositoryException.printStackTrace();//todo
            content.setRequestAttribute("acceptOrderResult","уже занято");

        }

        return router;
    }
}
