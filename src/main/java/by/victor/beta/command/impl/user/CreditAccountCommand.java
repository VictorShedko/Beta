package by.victor.beta.command.impl.user;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;
import by.victor.beta.validator.Validator;

public class CreditAccountCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        Router router=new Router(PagePath.CREATE_ORDER_RESULT);
        String username=(String)  content.getSessionAttribute(AttributeName.USERNAME);
        String sumAsString=(String) content.getRequestParameter(AttributeName.CREDIT_SUM);
        try {
            Validator validator=new Validator();
            if(validator.isValidCreditSum(sumAsString)) {
                int sum=Integer.parseInt(sumAsString);
                ServiceFacade.instance.creditAccount(username, sum);
                content.setRequestAttribute(AttributeName.COMMAND_RESULT, PageContentKey.SUCCESSFULLY);
            }else {
                router=new Router(PagePath.CREDIT_FORM);
                content.setRequestAttribute(AttributeName.FEEDBACK,validator.getInvalidFeedback());
            }
        } catch (ServiceException repositoryException) {
            content.setRequestAttribute(AttributeName.FEEDBACK, PageContentKey.SERVER_ERROR);

        }
        return router;
    }
}
