package by.victor.beta.logic.command.impl;

import by.victor.beta.controller.PagePathProvider;
import by.victor.beta.logic.command.AbstractCommand;
import by.victor.beta.logic.command.Router;
import by.victor.beta.logic.command.requsttransmiter.RequestParameter;
import by.victor.beta.logic.command.requsttransmiter.parametersforcomand.LoginParameter;
import by.victor.beta.logic.entity.Role;
import by.victor.beta.logic.service.ServiceFacade;

public class LoginCommand extends AbstractCommand {
    @Override
    public Router execute(RequestParameter parameters) {
    if(ServiceFacade.instance.login(parameters.getUsername(),((LoginParameter)parameters).getPassword())!= Role.DEFAULT){
        return new Router(PagePathProvider.CUSTOMER_MAIN_PAGE);
    }else {
        return new Router(PagePathProvider.ERROR_PAGE);
    }
    }
}
