package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;
import by.victor.beta.validator.Validator;

public class CreditAccountCommand implements Command {
   private static PRGParameterManager parameterManager=new PRGParameterManager();
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router;
        String path;
        String username=(String)  content.getSessionAttribute(AttributeName.USERNAME);
        String sumAsString=(String) content.getRequestParameter(AttributeName.CREDIT_SUM);
        try {
            Validator validator=new Validator();
            if(validator.isValidCreditSum(sumAsString)) {
                int sum=Integer.parseInt(sumAsString);
                ServiceFacade.INSTANCE.creditAccount(username, sum);
                path=parameterManager.addParameter(PagePath.PRG_RESULT,AttributeName.COMMAND_RESULT, PageContentKey.SUCCESSFULLY);
            }else {
                path=parameterManager.addParameter(PagePath.PRG_RESULT,AttributeName.COMMAND_RESULT, PageContentKey.SUCCESSFULLY);
            }
        } catch (ServiceException repositoryException) {
            path=parameterManager.addParameter(PagePath.PRG_RESULT,AttributeName.COMMAND_RESULT, PageContentKey.SUCCESSFULLY);

        }
        router=new Router(path);
        router.setRedirect();
        return router;
    }
}
