package by.victor.beta.command.impl.document;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import java.io.File;

public class CheckDocumentCommand implements AbstractCommand {//todo
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router = new Router(PagePathProvider.RESULT);
        String username = (String) content.getSessionAttribute(AttributeNameProvider.USERNAME);
        int id = Integer.parseInt((String) content.getRequestParameter(AttributeNameProvider.DOCUMENT_ID));
        try {
            ServiceFacade.instance.checkDocument(id, username);
            content.setRequestAttribute(AttributeNameProvider.COMMAND_RESULT, "успешно ");//todo в константы

        } catch (ServiceException repositoryException) {
            repositoryException.printStackTrace();//todo
            content.setRequestAttribute(AttributeNameProvider.COMMAND_RESULT, "провал ");//todo в константы

        }

        return router;
    }
}
