package by.victor.beta.logic.command.requsttransmiter;

import by.victor.beta.logic.command.AbstractCommand;

import javax.servlet.http.HttpServletRequest;


public class Transmiter {
    public RequestParameter buildData(HttpServletRequest request, AbstractCommand command){
       return StrategyProvider.instance.getStrategy(command).transmit(request);
    }
}
