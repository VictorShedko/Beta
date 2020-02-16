package by.victor.beta.command.impl;

import by.victor.beta.command.PagePath;
import by.victor.beta.command.Command;
import by.victor.beta.command.RequestSessionContent;
import by.victor.beta.command.Router;

public class DebugCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) {
        return new Router(PagePath.ERROR);
    }
}
