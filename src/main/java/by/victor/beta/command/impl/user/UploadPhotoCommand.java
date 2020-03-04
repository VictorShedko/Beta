package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.entity.User;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import java.io.File;

public class UploadPhotoCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router=new Router(PagePath.PRG_TO_USER_MENU);
        router.setRedirect();
        String username=(String)  content.getSessionAttribute(AttributeName.USERNAME);
        File file=content.getFile();
        try {
           User user= ServiceFacade.INSTANCE.uploadPhoto(file, username);
            content.setSessionAttribute(AttributeName.PHOTO_PATH,user.getPhotoPath());

        } catch (ServiceException ex) {
            content.setRequestAttribute(AttributeName.ERROR_MESSAGE_VALUE,"wrong file format" +
                    "" +
                    "");
            router=new Router(PagePath.ERROR);
        }

        return router;
    }
}
