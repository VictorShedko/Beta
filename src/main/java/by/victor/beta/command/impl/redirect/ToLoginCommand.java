package by.victor.beta.command.impl.redirect;

import by.victor.beta.command.PagePathProvider;
import by.victor.beta.command.AbstractCommand;
import by.victor.beta.command.RequestSessionContent;
import by.victor.beta.command.Router;

public class ToLoginCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) {
        content.setInvalidate(true);
        return new Router(PagePathProvider.LOGIN_PAGE);
    }
}
