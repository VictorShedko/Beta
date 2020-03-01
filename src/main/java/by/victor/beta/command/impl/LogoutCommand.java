package by.victor.beta.command.impl;

import by.victor.beta.command.*;

public class LogoutCommand implements Command {
    private  static PRGParameterManager prgParameterManager=new PRGParameterManager();
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        content.setInvalidate(true);
        if(content.getRequestParameter(AttributeName.FEEDBACK)!=null){
        content.setRequestAttribute(AttributeName.FEEDBACK,content.getRequestParameter(AttributeName.FEEDBACK));
        }
        Router router=new Router(PagePath.LOGIN);
        return router;
    }
}
