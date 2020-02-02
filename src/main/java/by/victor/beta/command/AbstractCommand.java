package by.victor.beta.command;



public interface  AbstractCommand {
  default  Router execute( RequestSessionContent content) throws CommandException{
      return new Router(PagePathProvider.ERROR_PAGE);
  }



}
