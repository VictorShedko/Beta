package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.entity.Document;
import by.victor.beta.entity.Role;
import by.victor.beta.entity.User;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import java.util.List;

public class ShowUserProfileCommand implements Command {

    public Router execute(RequestSessionContent content) throws CommandException {
        String username = (String) content.getRequestParameter(AttributeName.SEARCH_NAME);
        User user = null;
        try {
            user = ServiceFacade.INSTANCE.findUserByUsername(username);
        } catch (ServiceException e) {
            throw new CommandException();
        }
        content.setRequestAttribute(AttributeName.USER_INFO, user);

        if(user.getPhotoPath()!=null) {
            content.setRequestAttribute(AttributeName.USER_PROFILE_PHOTO_PATH, user.getPhotoPath());
        }else {
            content.setRequestAttribute(AttributeName.USER_PROFILE_PHOTO_PATH,
                    AttributeName.DEFAULT_PHOTO_PATH );
        }

        if (user.getRole() == Role.EXECUTOR) {
            List<Document> documentList = null;
            try {
                documentList = ServiceFacade.INSTANCE.showUserDocument(user.getUsername());
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            content.setRequestAttribute(AttributeName.DOCUMENT_LIST, documentList);
        }

        return new Router(PagePath.USER_PROFILE);
    }
}

