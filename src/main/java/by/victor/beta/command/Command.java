package by.victor.beta.command;


/**
 * The interface Command.
 */
public interface Command {
   /**
    * @param content all request and session attributes and parameters
    * @return router - router
    */
   Router execute( RequestSessionContent content) throws CommandException;
}
