package by.victor.beta.logic.command;

import by.victor.beta.logic.command.impl.DebugCommand;
import by.victor.beta.logic.command.impl.LoginCommand;
import by.victor.beta.logic.command.impl.redirect.ToRegistrationRedirectCommand;


public enum CommandType {
    LOGIN(new LoginCommand()),
    REGISTER(new DebugCommand()),
    TO_REGISTRATION(new ToRegistrationRedirectCommand()),
    LOGOUT(new DebugCommand()),
    VERIFY(new DebugCommand()),
    DELETE_USER(new DebugCommand()),
    SHOW_ORDERS_CUSTOMER(new DebugCommand()),
    ADD_ORDER(new DebugCommand()),
    RATE_OFFER(new DebugCommand()),
    ACCEPT_ORDER(new DebugCommand()),
    DELETE_ORDER(new DebugCommand()),
    SHOW_USER_INFO(new DebugCommand());


    private AbstractCommand command;
    CommandType(AbstractCommand command){
        this.command=command;
    }
    public AbstractCommand getCommand(){
        return command;
    }
}
