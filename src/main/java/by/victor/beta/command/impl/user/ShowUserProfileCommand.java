package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.entity.Document;
import by.victor.beta.entity.Role;
import by.victor.beta.entity.User;
import by.victor.beta.entity.UserStatus;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import java.util.List;

public class ShowUserProfileCommand implements AbstractCommand {

    public Router execute(RequestSessionContent content) throws CommandException {
        String username = (String) content.getRequestParameter(AttributeNameProvider.SEARCH_NAME);
        User user = null;
        try {
            user = ServiceFacade.instance.findUserByUsername(username);
        } catch (ServiceException e) {
            throw new CommandException();
        }
        content.setRequestAttribute(AttributeNameProvider.USER_INFO, user);

        if(user.getPhotoPath()!=null) {//todo optional
            content.setRequestAttribute(AttributeNameProvider.USER_PROFILE_PHOTO_PATH, user.getPhotoPath());
        }else {
            content.setRequestAttribute(AttributeNameProvider.USER_PROFILE_PHOTO_PATH,
                    AttributeNameProvider.DEFAULT_PHOTO_PATH );
        }

        if (user.getRole() == Role.EXECUTOR) {
            List<Document> documentList = null;
            try {
                documentList = ServiceFacade.instance.showUserDocument(user.getUsername());
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            content.setRequestAttribute(AttributeNameProvider.DOCUMENT_LIST, documentList);
        }

        return new Router(PagePathProvider.USER_PROFILE);
    }
}

