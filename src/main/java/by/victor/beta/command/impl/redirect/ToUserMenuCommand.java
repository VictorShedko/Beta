package by.victor.beta.command.impl.redirect;

import by.victor.beta.command.AbstractCommand;
import by.victor.beta.command.PagePathProvider;
import by.victor.beta.command.RequestSessionContent;
import by.victor.beta.command.Router;

public class ToUserMenuCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) {
        return new Router(PagePathProvider.USER_MAIN_PAGE);
    }

}
