package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

public class DeleteUserCommand implements Command {
    private static PRGParameterManager parameterManager=new PRGParameterManager();
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        String path;
        String username=(String)  content.getRequestParameter(AttributeName.USERNAME);
        try {
            ServiceFacade.INSTANCE.deleteUser(username);
            path=parameterManager.addParameter(PagePath.PRG_RESULT,AttributeName.COMMAND_RESULT,
                    PageContentKey.SUCCESSFULLY);
        } catch (ServiceException ex) {
            path=parameterManager.addParameter(PagePath.PRG_RESULT,AttributeName.COMMAND_RESULT,
                    PageContentKey.FAILED);
        }
        Router router=new Router(path);
        router.setRedirect();
        return router;
    }
}
