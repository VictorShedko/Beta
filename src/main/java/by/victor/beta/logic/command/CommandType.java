package by.victor.beta.logic.command;

import by.victor.beta.logic.command.impl.DebugComand;
import by.victor.beta.logic.command.impl.LoginCommand;
import by.victor.beta.logic.command.impl.RegistrationRedirectCommand;


public enum CommandType {
    LOGIN(new LoginCommand()),
    REGISTER(new DebugComand()),
    TO_REGISTRATION(new RegistrationRedirectCommand()),
    LOGOUT(new DebugComand()),
    VERIFY(new DebugComand()),
    SHOW_ORDERS(new DebugComand()),
    SHOW_OFFERS(new DebugComand()),
    ADD_ORDER(new DebugComand()),
    ADD_OFFER(new DebugComand()),
    RATE_OFFER(new DebugComand());


    private AbstractCommand command;
    CommandType(AbstractCommand command){
        this.command=command;
    }
    public AbstractCommand getCommand(){
        return command;
    }
}
