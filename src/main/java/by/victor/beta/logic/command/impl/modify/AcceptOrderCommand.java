package by.victor.beta.logic.command.impl.modify;

import by.victor.beta.logic.command.AbstractCommand;
import by.victor.beta.logic.command.CommandException;
import by.victor.beta.logic.command.RequestSessionContent;
import by.victor.beta.logic.command.Router;

public class AcceptOrderCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) throws CommandException {
        return null;
    }
}