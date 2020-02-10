package by.victor.beta.validator;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Validator {
    private StringBuilder invalidFeedback = new StringBuilder();//todo error message to constant

    private static final String VALID_USERNAME_REGEXP = "[a-zA-Z0-9/-]+([ ]*[a-zA-Z0-9/-]+)";
    private static final String VALID_LOGIN_REGEXP = "[a-zA-Z0-9/-]+";
    private static final String TEXT_WITH_NUMBERS_REGEXP = "[a-zA-Z0-9/-]+([0-9]*[a-zA-Z0-9/-]+)";
    private static final String VALID_DESCRIPTION_REGEXP = "[a-zA-Z0-9/-/(/)]+)";

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

    private void addToFeedBack(String errorMessage) {
        invalidFeedback.append(errorMessage);
        invalidFeedback.append(" ");
    }

    private boolean isValidName(String username) {
        boolean result = true;
        if (!username.matches(VALID_USERNAME_REGEXP)) {
            addToFeedBack("не корректное имя");//todo вынести в константы а еще лучше в ключи сообщений об ошибке
            result = false;
        }
        if ( username.length() > MAX_USERNAME_SIZE) {
            addToFeedBack("превышена максимальная длинна имени");
            result = false;
        }
        if (username.length() < MIN_USERNAME_SIZE) {
            addToFeedBack("имя должено быть длинее 7 символов");
            result = false;
        }
        return result;
    }

    private boolean isValidLogin(String login) {
        boolean result = true;
        if (!login.matches(VALID_LOGIN_REGEXP)) {
            addToFeedBack("логин должен содержать циферы и буквы ");
            result = false;
        }
        if ( login.length() > MAX_LOGIN_SIZE) {
            addToFeedBack("превышена максимальная длинна логина");
            result = false;
        }
        if (login.length() < MIN_LOGIN_SIZE) {
            addToFeedBack("логин должен быть длинее 7 символов");
            result = false;
        }
        return result;
    }

    private boolean isValidPassword(String password) {
        boolean result = true;

        if (!password.matches(TEXT_WITH_NUMBERS_REGEXP)) {
            addToFeedBack("пароль должен содержать циферы и буквы ");
            result = false;
        }
        if ( password.length() > MAX_PASSWORD_SIZE) {
            addToFeedBack("превышена максимальная длинна пароля");
            result = false;
        }
        if (password.length() < MIN_PASSWORD_SIZE) {
            addToFeedBack("пароль должен быть длинее 7 символов");
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
            addToFeedBack("bad time ");
            result = false;
        }
        if (startDate.after(endDate)) {
            addToFeedBack("start time must be before end time ");
            result = false;
        }
        if (endDate.getTime() > startDate.getTime() + ONE_YEAR) {
            addToFeedBack("bad time ");
            result = false;
        }
        return result;
    }

    private boolean isValidAddress(String address) {
        boolean result = true;
        if (!address.matches(VALID_USERNAME_REGEXP)) {
            addToFeedBack("exceeded max notify size");
            result = false;
        }

        if (address.length() > MAX_ADDRESS_SIZE) {
            addToFeedBack("exceeded max description size");
            result = false;
        }
        return result;
    }

    private boolean isValidCreditSum(long sum) {
        boolean result = true;
        if (sum > MAX_SUM) {
            addToFeedBack("");
            result = false;
        }
        if (sum <= 0) {
            addToFeedBack("");
            result = false;
        }

        return result;
    }


    private boolean isValidDescription(String description) {
        boolean result = true;
        if (!description.matches(VALID_USERNAME_REGEXP)) {
            addToFeedBack("exceeded max notify size");
            result = false;
        }

        if (description.length() > MAX_DESCRIPTION_SIZE) {
            addToFeedBack("exceeded max description size");
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
            addToFeedBack("exceeded max notify size");
            result = false;
        }
        return result;
    }


    public String getInvalidFeedback() {
        return invalidFeedback.toString();
    }
}
