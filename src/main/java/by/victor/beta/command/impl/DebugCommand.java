package by.victor.beta.command.impl;

import by.victor.beta.command.PagePathProvider;
import by.victor.beta.command.AbstractCommand;
import by.victor.beta.command.RequestSessionContent;
import by.victor.beta.command.Router;

public class DebugCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) {
        return new Router(PagePathProvider.ERROR_PAGE);
    }
}
