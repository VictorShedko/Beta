package by.victor.beta.logic.command.impl.redirect;

import by.victor.beta.logic.command.AbstractCommand;
import by.victor.beta.logic.command.PagePathProvider;
import by.victor.beta.logic.command.RequestSessionContent;
import by.victor.beta.logic.command.Router;

public class ToOrderCreateFormCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent parameters) {
        return new Router(PagePathProvider.CREATE_ORDER_FORM);
    }
}
