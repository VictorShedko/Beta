package by.victor.beta.command.impl.document;

import by.victor.beta.command.*;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import java.io.File;

public class UploadDocumentCommand implements AbstractCommand {//todo

    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router = new Router(PagePathProvider.USER_MAIN_PAGE);
        String username = (String) content.getSessionAttribute(AttributeNameProvider.USERNAME);
        File file = content.getFile();
        try {
            ServiceFacade.instance.addDocument(file, username);
            content.setRequestAttribute("acceptOrderResult", "успешно ");//todo в константы

        } catch (ServiceException repositoryException) {
            repositoryException.printStackTrace();//todo


        }

        return router;
    }
}