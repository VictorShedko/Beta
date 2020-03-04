package by.victor.beta.command.impl.redirect;

        import by.victor.beta.command.*;


public class ToRegistrationCommand implements Command {

    @Override
    public Router execute(RequestSessionContent content) {
        if(content.getRequestParameter(AttributeName.FEEDBACK)!=null){
            content.setRequestAttribute(AttributeName.FEEDBACK,content.getRequestParameter(AttributeName.FEEDBACK));
        }
        return new Router(PagePath.REGISTRATION_FORM);
    }
}
