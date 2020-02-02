package by.victor.beta.command.impl.redirect;

import by.victor.beta.command.*;

public class ToAddInfoCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {


        return new Router(PagePathProvider.ADD_INFO_FORM);
    }
}
