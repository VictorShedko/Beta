package by.victor.beta.command.impl.redirect;

import by.victor.beta.command.*;

public class ToAddInfoCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {


        return new Router(PagePath.ADD_INFO_FORM);
    }
}
