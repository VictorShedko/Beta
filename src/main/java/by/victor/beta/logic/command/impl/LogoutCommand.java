package by.victor.beta.logic.command.impl;

import by.victor.beta.logic.command.PagePathProvider;
import by.victor.beta.logic.command.AbstractCommand;
import by.victor.beta.logic.command.CommandException;
import by.victor.beta.logic.command.RequestSessionContent;
import by.victor.beta.logic.command.Router;

public class LogoutCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        content.setInvalidate(true);
        return new Router(PagePathProvider.LOGIN_PAGE);
    }
}
