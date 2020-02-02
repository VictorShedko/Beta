package by.victor.beta.command.impl;

import by.victor.beta.command.PagePathProvider;
import by.victor.beta.command.AbstractCommand;
import by.victor.beta.command.CommandException;
import by.victor.beta.command.RequestSessionContent;
import by.victor.beta.command.Router;

public class LogoutCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        content.setInvalidate(true);
        return new Router(PagePathProvider.LOGIN_PAGE);
    }
}
