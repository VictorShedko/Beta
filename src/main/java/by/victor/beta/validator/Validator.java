package by.victor.beta.validator;

import by.victor.beta.command.PageContentKey;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Validator {
    private String invalidFeedback ;

    private static final String VALID_USERNAME_REGEXP = "[a-zA-Z0-9/-]+([ ]*[a-zA-Z0-9/-]+)";
    private static final String VALID_LOGIN_REGEXP = "[a-zA-Z0-9/-]+";
    private static final String TEXT_WITH_NUMBERS_REGEXP = "[a-zA-Z0-9/-]+([0-9]*[a-zA-Z0-9/-]+)";
    private static final String TEXT_WITH_NUMBERS_AND_SPACES_REGEXP = "[a-z\\sA-Zа-яА-Я0-9/-]+";
    private static final int MAX_USERNAME_SIZE = 30;
    private static final int MIN_USERNAME_SIZE = 8;
    private static final int MAX_LOGIN_SIZE = 30;
    private static final int MIN_LOGIN_SIZE = 8;
    private static final int MAX_PASSWORD_SIZE = 160;
    private static final int MIN_PASSWORD_SIZE = 8;
    private static final int MAX_DESCRIPTION_SIZE = 140;
    private static final int MAX_NOTIFY_SIZE = 140;
    private static final long MAX_SUM = Integer.MAX_VALUE;
    private static final long MAX_ADDRESS_SIZE = 140;

    private static final long ONE_HOUR = TimeUnit.HOURS.toMillis(1);
    private static final long ONE_YEAR = TimeUnit.DAYS.toMillis(365);

    private void setFeedBackKey(String errorKey) {
        invalidFeedback=errorKey;
    }

    private boolean isValidName(String username) {
        boolean result = true;
        if (!username.matches(VALID_USERNAME_REGEXP)) {
            setFeedBackKey(PageContentKey.INVALID_USERNAME);//todo вынести в константы а еще лучше в ключи сообщений об ошибке
            result = false;
        }
        if ( username.length() > MAX_USERNAME_SIZE) {
            setFeedBackKey(PageContentKey.USERNAME_MAXIMUM_LENGTH_EXCEEDED);
            result = false;
        }
        if (username.length() < MIN_USERNAME_SIZE) {
            setFeedBackKey(PageContentKey.TOO_SHORT_USERNAME);
            result = false;
        }
        return result;
    }

    private boolean isValidLogin(String login) {
        boolean result = true;
        if (!login.matches(VALID_LOGIN_REGEXP)) {
            setFeedBackKey("логин должен содержать циферы и буквы ");
            result = false;
        }
        if ( login.length() > MAX_LOGIN_SIZE) {
            setFeedBackKey("превышена максимальная длинна логина");
            result = false;
        }
        if (login.length() < MIN_LOGIN_SIZE) {
            setFeedBackKey("логин должен быть длинее 7 символов");
            result = false;
        }
        return result;
    }

    private boolean isValidPassword(String password) {
        boolean result = true;

        if (!password.matches(TEXT_WITH_NUMBERS_REGEXP)) {
            setFeedBackKey("пароль должен содержать циферы и буквы ");
            result = false;
        }
        if ( password.length() > MAX_PASSWORD_SIZE) {
            setFeedBackKey("превышена максимальная длинна пароля");
            result = false;
        }
        if (password.length() < MIN_PASSWORD_SIZE) {
            setFeedBackKey("пароль должен быть длинее 7 символов");
            result = false;
        }
        return result;
    }

    public boolean isValidRegistrationForm(String username, String password, String login) {//todo email
        return isValidLogin(login) && isValidName(username) && isValidPassword(password);
    }

    public boolean isValidNotifyForm() {
        return true;
    }


    private boolean isValidOrderTime(Date startDate, Date endDate)//todo correct?
    {
        boolean result = true;
        Date currentDate = new Date();

        if (currentDate.getTime() - startDate.getTime() > ONE_HOUR) {
            setFeedBackKey("bad time ");
            result = false;
        }
        if (startDate.after(endDate)) {
            setFeedBackKey("start time must be before end time ");
            result = false;
        }
        if (endDate.getTime() > startDate.getTime() + ONE_YEAR) {
            setFeedBackKey("bad time ");
            result = false;
        }
        return result;
    }

    private boolean isValidAddress(String address) {
        boolean result = true;
        if (!address.matches(TEXT_WITH_NUMBERS_AND_SPACES_REGEXP)) {
            setFeedBackKey("exceeded max notify size");
            result = false;
        }

        if (address.length() > MAX_ADDRESS_SIZE) {
            setFeedBackKey("exceeded max description size");
            result = false;
        }
        return result;
    }

    public boolean isValidCreditSum(String sumAsString) {
        boolean result = true;
        int sum;
        try {
            sum=Integer.parseInt(sumAsString);
        }catch (NumberFormatException e){
            setFeedBackKey("'"+sumAsString+"'is not a number");
            return false;
        }


        if (sum > MAX_SUM) {
            setFeedBackKey("");
            result = false;
        }
        if (sum <= 0) {
            setFeedBackKey("must be positive");
            result = false;
        }

        return result;
    }


    private boolean isValidDescription(String description) {
        boolean result = true;
        if (!description.matches(TEXT_WITH_NUMBERS_AND_SPACES_REGEXP)) {
            setFeedBackKey("exceeded max notify size");
            result = false;
        }

        if (description.length() > MAX_DESCRIPTION_SIZE) {
            setFeedBackKey("exceeded max description size");
            result = false;
        }
        return result;

    }

    public boolean isValidOrderForm(Date start, Date end, String address, String description) {
        return isValidOrderTime(start, end) && (end.after(start)) && (isValidAddress(address)) && (isValidDescription(description));
    }

    public boolean isValidNotify(String notifyText) {
        boolean result = true;
        if (notifyText.length() > MAX_NOTIFY_SIZE) {
            setFeedBackKey("exceeded max notify size");
            result = false;
        }
        return result;
    }


    public String getInvalidFeedback() {
        return invalidFeedback;
    }
}
