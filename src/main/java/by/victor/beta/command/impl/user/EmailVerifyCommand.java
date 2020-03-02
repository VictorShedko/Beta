package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;


public class EmailVerifyCommand implements Command {

    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        String verifyCode = (String) content.getRequestParameter(AttributeName.VERIFY_CODE);
        Router router;
        try {
            String path;
            PRGParameterManager manager=new PRGParameterManager();
            if(ServiceFacade.INSTANCE.emailVerify(verifyCode)) {
                path=manager.addParameter(PagePath.PRG_TO_LOGIN,AttributeName.FEEDBACK,PageContentKey.SUCCESSFULLY);
            }else {
                path=manager.addParameter(PagePath.PRG_TO_LOGIN,AttributeName.FEEDBACK,PageContentKey.FAILED);
            }
            router = new Router(path);
            return router;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
