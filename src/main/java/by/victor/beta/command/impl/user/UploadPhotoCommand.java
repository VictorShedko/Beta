package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import java.io.File;

public class UploadPhotoCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router=new Router(PagePathProvider.USER_MAIN_PAGE);
        String username=(String)  content.getSessionAttribute(AttributeNameProvider.USERNAME);
        File file=content.getFile();
        try {
            ServiceFacade.instance.uploadPhoto(file, username);
            content.setRequestAttribute("acceptOrderResult","успешно ");//todo в константы

        } catch (ServiceException ex) {
            ex.printStackTrace();//todo


        }

        return router;
    }
}
