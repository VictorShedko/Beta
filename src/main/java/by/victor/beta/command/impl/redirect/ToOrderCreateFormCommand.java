package by.victor.beta.command.impl.redirect;

import by.victor.beta.command.Command;
import by.victor.beta.command.PagePath;
import by.victor.beta.command.RequestSessionContent;
import by.victor.beta.command.Router;

public class ToOrderCreateFormCommand implements Command {
    @Override
    public Router execute(RequestSessionContent parameters) {
        return new Router(PagePath.CREATE_ORDER_FORM);
    }
}
