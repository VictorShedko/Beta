package by.victor.beta.command.impl.redirect;

import by.victor.beta.command.Command;
import by.victor.beta.command.PagePath;
import by.victor.beta.command.RequestSessionContent;
import by.victor.beta.command.Router;

public class ToCreditFormCommand implements Command {
    @Override
    public Router execute(RequestSessionContent parameters) {
        return new Router(PagePath.CREDIT_FORM);
    }
}


