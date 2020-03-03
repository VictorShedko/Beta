package by.victor.beta.command.impl.order;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;
import by.victor.beta.validator.Validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateOrderCommand implements Command {
    private static PRGParameterManager manager=new PRGParameterManager();
    @Override
    public Router execute(RequestSessionContent content) {
        Router router;
        String path;
        String address = (String) content.getRequestParameter(AttributeName.ADDRESS);
        String description = (String) content.getRequestParameter(AttributeName.DESCRIPTION);
        String username = (String) content.getSessionAttribute(AttributeName.USERNAME);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        Date startTime;
        Date endTime;
        try {
            startTime = formatter.parse((String) content.getRequestParameter(AttributeName.START_TIME));
            endTime = formatter.parse((String) content.getRequestParameter(AttributeName.END_TIME));
        } catch (ParseException e) {
            path=manager.addParameter(PagePath.CREDIT_FORM,AttributeName.COMMAND_RESULT, PageContentKey.INVALID_DATE_FORMAT);
            return new Router(path);
        }

        String priceAsString = (String) content.getRequestParameter(AttributeName.PRICE);
        try {
            Validator validator = new Validator();
            if (validator.isValidOrderForm(startTime, endTime, address, description,priceAsString)) {
                int price = Integer.parseInt((String) content.getRequestParameter(AttributeName.PRICE));
                if (ServiceFacade.INSTANCE.createOrder(address, description, username, startTime, endTime, price)) {
                    path=manager.addParameter(PagePath.PRG_RESULT,AttributeName.COMMAND_RESULT, PageContentKey.SUCCESSFULLY);
                } else {
                    path=manager.addParameter(PagePath.PRG_RESULT,AttributeName.COMMAND_RESULT, PageContentKey.NOT_ENOUGH_CASH);
                }
            } else {
                path=manager.addParameter(PagePath.PRG_RESULT,AttributeName.COMMAND_RESULT, validator.getInvalidFeedback());
            }
        } catch (ServiceException e) {
            path=manager.addParameter(PagePath.PRG_RESULT,AttributeName.COMMAND_RESULT, PageContentKey.SERVER_ERROR);
        }
        router= new Router(path);
        router.setRedirect();
        return router;
    }
}
