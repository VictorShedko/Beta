package by.victor.beta.logic.command.impl;

import by.victor.beta.controller.PagePathProvider;
import by.victor.beta.logic.command.AbstractCommand;
import by.victor.beta.logic.command.Router;
import by.victor.beta.logic.command.requsttransmiter.RequestParameter;

public class RegistrationRedirectCommand extends AbstractCommand {

    @Override
    public Router execute(RequestParameter parameters) {
        return new Router(PagePathProvider.REGISTRATION_PAGE);
    }
}
