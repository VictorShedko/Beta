package by.victor.beta.logic.command;



public interface  AbstractCommand {
    Router execute( RequestSessionContent content) throws CommandException;



}
