package by.victor.beta.command.impl.redirect;

import by.victor.beta.command.*;

public class ToOperationResultCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        if (content.getRequestParameter(AttributeName.COMMAND_RESULT) != null) {
            content.setRequestAttribute(AttributeName.COMMAND_RESULT,
                    content.getRequestParameter(AttributeName.COMMAND_RESULT));
        }
        return new Router(PagePath.RESULT);
    }
}
