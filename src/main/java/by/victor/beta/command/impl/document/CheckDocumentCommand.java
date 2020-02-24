package by.victor.beta.command.impl.document;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

public class CheckDocumentCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router = new Router(PagePath.RESULT);
        String username = (String) content.getSessionAttribute(AttributeName.USERNAME);
        int id = Integer.parseInt((String) content.getRequestParameter(AttributeName.DOCUMENT_ID));
        try {
            ServiceFacade.INSTANCE.checkDocument(id, username);
            content.setRequestAttribute(AttributeName.COMMAND_RESULT, PageContentKey.SUCCESSFULLY);
        } catch (ServiceException ex) {
            content.setRequestAttribute(AttributeName.COMMAND_RESULT, PageContentKey.FAILED);
        }
        return router;
    }
}
