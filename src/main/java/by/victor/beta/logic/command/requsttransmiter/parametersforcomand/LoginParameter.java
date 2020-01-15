package by.victor.beta.logic.command.requsttransmiter.parametersforcomand;

import by.victor.beta.logic.command.requsttransmiter.RequestParameter;

public class LoginParameter extends RequestParameter {
private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
