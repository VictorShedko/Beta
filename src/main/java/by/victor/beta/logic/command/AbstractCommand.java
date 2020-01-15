package by.victor.beta.logic.command;

import by.victor.beta.logic.command.requsttransmiter.RequestParameter;

public abstract class  AbstractCommand {
     public abstract Router execute( RequestParameter parameters);



}
