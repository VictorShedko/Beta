package by.victor.beta.logic.command.requsttransmiter;

import by.victor.beta.logic.command.AbstractCommand;

import javax.servlet.http.HttpServletRequest;

public interface TransmitStrategy {
    RequestParameter transmit( HttpServletRequest request);
}
