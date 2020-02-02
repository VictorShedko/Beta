package by.victor.beta.command.impl.redirect;

import by.victor.beta.command.AbstractCommand;
import by.victor.beta.command.PagePathProvider;
import by.victor.beta.command.RequestSessionContent;
import by.victor.beta.command.Router;

public class ToCreditFormCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent parameters) {
        return new Router(PagePathProvider.CREDIT_FORM);
    }
}


