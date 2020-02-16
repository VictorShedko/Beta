package by.victor.beta.command;



public interface Command {
   Router execute( RequestSessionContent content) throws CommandException;
}
