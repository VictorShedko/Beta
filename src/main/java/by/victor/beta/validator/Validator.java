package by.victor.beta.validator;

import by.victor.beta.command.AttributeNameProvider;
import by.victor.beta.command.RequestSessionContent;
import by.victor.beta.entity.Role;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class Validator {
    public static final String VALID_USERNAME_REGEXP="[a-zA-Z0-9/-]+([ ]*[a-zA-Z0-9/-]+)";
    public static final String VALID_LOGIN_REGEXP="[a-zA-Z0-9/-]+";
    public static final String VALID_PASSWORD_REGEXP="[a-zA-Z0-9/-]+([0-9]*[a-zA-Z0-9/-]+)";
    private static final int MAX_USERNAME_SIZE=30;
    private static final int MIN_USERNAME_SIZE=8;
    private static final int MAX_LOGIN_SIZE=30;
    private static final int MIN_LOGIN_SIZE=8;
    private static final int MAX_PASSWORD_SIZE=160;
    private static final int MIN_PASSWORD_SIZE=8;
    private static final long ONE_HOUR= TimeUnit.HOURS.toMillis(1);
    private static final long ONE_YEAR=TimeUnit.DAYS.toMillis(365);

    private boolean isValidName(String username){
        return username.matches(VALID_USERNAME_REGEXP)&&username.length()<=MAX_USERNAME_SIZE
                &&username.length()>=MIN_USERNAME_SIZE;
    }

    private boolean isValidLogin(String username){
        return username.matches(VALID_LOGIN_REGEXP)&&username.length()<=MAX_LOGIN_SIZE
                &&username.length()>=MIN_LOGIN_SIZE;
    }

    private boolean isValidPassword(String username){
        return username.matches(VALID_PASSWORD_REGEXP)&&username.length()<=MAX_PASSWORD_SIZE
                &&username.length()>=MIN_PASSWORD_SIZE;
    }

    public boolean isValidRegistrationForm(String username,String password,String login){
        return isValidLogin(login)&&isValidName(username)&&isValidPassword(password);
    }

    public boolean isValidNotifyForm(){
        return true;
    }


    public boolean isValidTime(Date startDate,Date endDate)//todo correct?
    {
        Date currentDate=new Date();

        if(currentDate.getTime()-startDate.getTime()>ONE_HOUR){
         return false;
        }
        if(startDate.after(endDate)){ return false;
        }
        if(endDate.getTime()>startDate.getTime()+ONE_YEAR){
            return false;
        }
        return true;
    }

    public boolean isValidAddress(String address){
        return true;
    }

    public boolean isValidDescription(String description){
        return true;
    }

    public boolean isValidOrderForm(Date start,Date end,String address,String description){
        return isValidTime(start,end)&&(end.after(start))&&(isValidAddress(address))&&(isValidDescription(description));

    }

}
