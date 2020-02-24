package by.victor.beta.command.impl.document;

import by.victor.beta.command.*;
import by.victor.beta.entity.Document;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import java.io.File;
import java.util.List;

public class ShowMyDocumentsCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router = new Router(PagePath.USER_DOCUMENTS);
        String username = (String) content.getSessionAttribute(AttributeName.USERNAME);
        try {
            List<Document> documents = ServiceFacade.INSTANCE.showUserDocuments(username);
            content.setRequestAttribute(AttributeName.DOCUMENT_LIST, documents);
        } catch (ServiceException ex) {
            throw new CommandException();
        }
        return router;
    }
}
