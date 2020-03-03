package by.victor.beta.command.impl.document;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import java.io.File;

public class UploadDocumentCommand implements Command {
    private static PRGParameterManager manager=new PRGParameterManager();
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router;
        String username = (String) content.getSessionAttribute(AttributeName.USERNAME);
        File file = content.getFile();
        try {
            ServiceFacade.INSTANCE.addDocument(file, username);
            String path=manager.addParameter(PagePath.PRG_TO_USER_MENU,AttributeName.COMMAND_RESULT,
                    PageContentKey.SUCCESSFULLY);
            router= new Router(path);
            router.setRedirect();
        } catch (ServiceException ex) {
            throw new CommandException();
        }
        return router;
    }
}