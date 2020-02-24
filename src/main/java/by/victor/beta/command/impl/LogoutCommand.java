package by.victor.beta.command.impl;

import by.victor.beta.command.PagePath;
import by.victor.beta.command.Command;
import by.victor.beta.command.CommandException;
import by.victor.beta.command.RequestSessionContent;
import by.victor.beta.command.Router;

public class LogoutCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        content.setInvalidate(true);
        Router router=new Router(PagePath.INDEX);
        router.setRedirect();
        return router;
    }
}
