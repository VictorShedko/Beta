package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.entity.User;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import java.io.File;

public class UploadPhotoCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router=new Router(PagePath.USER_MAIN_MENU);
        String username=(String)  content.getSessionAttribute(AttributeName.USERNAME);
        File file=content.getFile();
        try {
           User user= ServiceFacade.INSTANCE.uploadPhoto(file, username);
            content.setRequestAttribute(AttributeName.PHOTO_PATH,user.getPhotoPath());

        } catch (ServiceException ex) {
            router=new Router(PagePath.ERROR);
        }

        return router;
    }
}
