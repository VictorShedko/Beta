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
    @Override
    public Router execute(RequestSessionContent content) {
        String address = (String) content.getRequestParameter(AttributeName.ADDRESS);
        String description = (String) content.getRequestParameter(AttributeName.DESCRIPTION);
        String username = (String) content.getSessionAttribute(AttributeName.USERNAME);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = formatter.parse((String) content.getRequestParameter(AttributeName.START_TIME));
            endTime = formatter.parse((String) content.getRequestParameter(AttributeName.END_TIME));
        } catch (ParseException e) {
            content.setRequestAttribute(AttributeName.FEEDBACK, PageContentKey.INVALID_DATE_FORMAT);
            return new Router(PagePath.CREDIT_FORM);
        }

        int price = Integer.parseInt((String) content.getRequestParameter(AttributeName.PRICE));
        try {
            Validator validator=new Validator();
            if(validator.isValidOrderForm(startTime,endTime,address,description)) {
                ServiceFacade.instance.createOrder(address, description, username, startTime, endTime, price);
                content.setRequestAttribute(AttributeName.COMMAND_RESULT, PageContentKey.SUCCESSFULLY);
            }else {
                content.setRequestAttribute(AttributeName.FEEDBACK,validator.getInvalidFeedback());
                return new Router(PagePath.CREATE_ORDER_FORM);
            }
        } catch (ServiceException e) {
            content.setRequestAttribute(AttributeName.FEEDBACK, PageContentKey.SERVER_ERROR);
            return new Router(PagePath.CREATE_ORDER_FORM);
        }

        return new Router(PagePath.CREATE_ORDER_RESULT);
    }
}
