package by.victor.beta.logic.command.requsttransmiter;

import by.victor.beta.logic.command.AbstractCommand;
import by.victor.beta.logic.command.impl.LoginCommand;
import by.victor.beta.logic.command.impl.RegistrationRedirectCommand;
import by.victor.beta.logic.command.requsttransmiter.parametersforcomand.LoginParameter;

import java.util.HashMap;

public enum  StrategyProvider {
    instance;
    private HashMap<Class,TransmitStrategy> strategyHashMap;
    public TransmitStrategy getStrategy(AbstractCommand command){
        return strategyHashMap.get(command.getClass());
    }
    StrategyProvider(){
        strategyHashMap= new HashMap<>();
        strategyHashMap.put(LoginCommand.class,(request)->{
            LoginParameter loginParameter=new LoginParameter();
            loginParameter.setPassword(request.getParameter("password"));
            loginParameter.setUsername(request.getParameter("username"));
            return  loginParameter;
        });
        strategyHashMap.put(RegistrationRedirectCommand.class,(request)->{
            return null;
            }
        );

    }
}
