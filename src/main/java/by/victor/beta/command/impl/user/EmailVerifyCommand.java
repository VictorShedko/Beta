package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;


public class EmailVerifyCommand implements Command {

    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        String verifyCode = (String) content.getRequestParameter(AttributeName.VERIFY_CODE);
        Router router = new Router(PagePath.PRG_TO_LOGIN);
        try {
            if(ServiceFacade.INSTANCE.emailVerify(verifyCode)) {
                content.setRequestParameter(AttributeName.ALERT_TEXT,"1");//todo


            }else {
                content.setRequestParameter(AttributeName.ALERT_TEXT,"2");//todo
            }
            return router;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
