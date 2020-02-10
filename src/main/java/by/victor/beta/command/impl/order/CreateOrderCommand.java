package by.victor.beta.command.impl.order;

import by.victor.beta.command.*;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;
import by.victor.beta.validator.Validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateOrderCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) {
        String address = (String) content.getRequestParameter(AttributeNameProvider.ADDRESS);
        String description = (String) content.getRequestParameter(AttributeNameProvider.DESCRIPTION);
        String username = (String) content.getSessionAttribute(AttributeNameProvider.USERNAME);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = formatter.parse((String) content.getRequestParameter(AttributeNameProvider.START_TIME));
            endTime = formatter.parse((String) content.getRequestParameter(AttributeNameProvider.END_TIME));
        } catch (ParseException e) {
            content.setRequestAttribute(AttributeNameProvider.FEEDBACK,"не верный формат даты");
            return new Router(PagePathProvider.CREDIT_FORM);  //todo допустимо?
        }
        int price = Integer.parseInt((String) content.getRequestParameter(AttributeNameProvider.PRICE));
        try {
            Validator validator=new Validator();
            if(validator.isValidOrderForm(startTime,endTime,address,description)) {
                ServiceFacade.instance.createOrder(address, description, username, startTime, endTime, price);
                content.setRequestAttribute(AttributeNameProvider.CREATE_ORDER_RESULT_MESSAGE, "заказ создан успешно");
                content.setRequestAttribute(AttributeNameProvider.CREATE_ORDER_RESULT_MESSAGE, "ошибка при создании");
            }else {
                content.setRequestAttribute(AttributeNameProvider.FEEDBACK,"не верный формат даты");
                return new Router(PagePathProvider.CREDIT_FORM);
            }
        } catch (ServiceException e) {
            content.setRequestAttribute(AttributeNameProvider.FEEDBACK,"не верный формат даты");
            return new Router(PagePathProvider.CREDIT_FORM);
        }

        return new Router(PagePathProvider.CREATE_ORDER_RESULT);
    }
}
