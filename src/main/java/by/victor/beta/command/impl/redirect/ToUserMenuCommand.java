package by.victor.beta.command.impl.redirect;

import by.victor.beta.command.*;

public class ToUserMenuCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) {
        if(content.getRequestParameter(AttributeName.FEEDBACK)!=null){
            content.setRequestAttribute(AttributeName.FEEDBACK,content.getRequestParameter(AttributeName.FEEDBACK));
        }
        return new Router(PagePath.USER_MAIN_MENU);
    }

}
