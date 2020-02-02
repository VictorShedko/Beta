package by.victor.beta.command.impl.order;

import by.victor.beta.command.*;
import by.victor.beta.entity.Order;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

public class AcceptOrderCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router=new Router(PagePathProvider.ACCEPT_ORDER_RESULT);;
        int orderId= Integer.parseInt( (String) content.getRequestParameter(AttributeNameProvider.ORDER));
        String username=(String)  content.getSessionAttribute(AttributeNameProvider.USERNAME);
        try {
            ServiceFacade.instance.acceptOrder(orderId,username);
            content.setRequestAttribute("acceptOrderResult","успешно ");//todo в константы
        } catch (ServiceException ex) {
          //todo
            content.setRequestAttribute("acceptOrderResult","уже занято");

        }

        return router;
    }
}
