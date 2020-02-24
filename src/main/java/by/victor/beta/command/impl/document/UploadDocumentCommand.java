package by.victor.beta.command.impl.document;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import java.io.File;

public class UploadDocumentCommand implements Command {

    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router = new Router(PagePath.USER_MAIN_MENU);
        String username = (String) content.getSessionAttribute(AttributeName.USERNAME);
        File file = content.getFile();
        try {
            ServiceFacade.INSTANCE.addDocument(file, username);
            content.setRequestAttribute(AttributeName.COMMAND_RESULT, PageContentKey.SUCCESSFULLY);
        } catch (ServiceException ex) {
            throw new CommandException();
        }
        return router;
    }
}