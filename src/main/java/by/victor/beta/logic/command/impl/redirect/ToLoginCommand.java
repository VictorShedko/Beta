package by.victor.beta.logic.command.impl.redirect;

import by.victor.beta.logic.command.PagePathProvider;
import by.victor.beta.logic.command.AbstractCommand;
import by.victor.beta.logic.command.RequestSessionContent;
import by.victor.beta.logic.command.Router;

public class ToLoginCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) {
        content.setInvalidate(true);
        return new Router(PagePathProvider.LOGIN_PAGE);
    }
}
