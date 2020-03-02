package by.victor.beta.validator;

import by.victor.beta.command.PageContentKey;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Validator {
    private static final String VALID_USERNAME_REGEXP = "[a-zA-Z0-9/-]+([ ]*[a-zA-Z0-9/-]+)";
    private static final String VALID_LOGIN_REGEXP = "[a-zA-Z0-9/-]+";
    private static final String TEXT_WITH_NUMBERS_REGEXP = "[a-zA-Z0-9/-]+([0-9]*[a-zA-Z0-9/-]+)";
    private static final String TEXT_WITH_NUMBERS_AND_SPACES_REGEXP = "[a-z\\sA-Zа-яА-Я0-9/-]+";
    private static final String EMAIL_REGEXP="^(.+)@(.+)$";
    private static final int MAX_USERNAME_SIZE = 30;
    private static final int MIN_USERNAME_SIZE = 8;
    private static final int MAX_LOGIN_SIZE = 30;
    private static final int MIN_LOGIN_SIZE = 8;
    private static final int MAX_PASSWORD_SIZE = 160;
    private static final int MIN_PASSWORD_SIZE = 8;
    private static final int MAX_DESCRIPTION_SIZE = 140;
    private static final long MAX_SUM = Integer.MAX_VALUE;
    private static final long MAX_ADDRESS_SIZE = 140;
    private static final long MIN_ADDRESS_SIZE = 8;
    private static final long ONE_HOUR = TimeUnit.HOURS.toMillis(1);
    private static final long ONE_YEAR = TimeUnit.DAYS.toMillis(365);

    private String invalidFeedback;

    private void setFeedBackKey(String errorKey) {
        invalidFeedback = errorKey;
    }

    private boolean isValidName(String username) {
        boolean result = true;
        if (!username.matches(VALID_USERNAME_REGEXP)) {
            setFeedBackKey(PageContentKey.INVALID_USERNAME);
            result = false;
        }
        if (username.length() > MAX_USERNAME_SIZE) {
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
            setFeedBackKey(PageContentKey.INVALID_LOGIN);
            result = false;
        }
        if (login.length() > MAX_LOGIN_SIZE || login.length() < MIN_LOGIN_SIZE) {
            setFeedBackKey(PageContentKey.INVALID_LOGIN_SIZE);
            result = false;
        }
        return result;
    }

    private boolean isValidPassword(String password) {
        boolean result = true;

        if (!password.matches(TEXT_WITH_NUMBERS_REGEXP)) {
            setFeedBackKey(PageContentKey.INVALID_PASSWORD);
            result = false;
        }
        if (password.length() > MAX_PASSWORD_SIZE || password.length() < MIN_PASSWORD_SIZE) {
            setFeedBackKey(PageContentKey.PASSWORD_SIZE);
            result = false;
        }

        return result;
    }

    public boolean isValidRegistrationForm(String username, String password, String login,String email) {
        return isValidLogin(login) && isValidName(username) && isValidPassword(password)&&isValidEmail(email);
    }

    private boolean isValidEmail(String email) {
        boolean result = true;

        if (!email.matches(EMAIL_REGEXP)) {
            setFeedBackKey(PageContentKey.INVALID_PASSWORD);
            result = false;
        }
        return result;
    }


    private boolean isValidOrderTime(Date startDate, Date endDate)
    {
        boolean result = true;
        Date currentDate = new Date();

        if (currentDate.getTime() - startDate.getTime() > ONE_HOUR) {
            setFeedBackKey(PageContentKey.LESS_THEN_ONE_HOUR_TO_ORDER_START);
            result = false;
        }
        if (startDate.after(endDate)) {
            setFeedBackKey(PageContentKey.END_BEFORE_START);
            result = false;
        }
        if (endDate.getTime() > startDate.getTime() + ONE_YEAR) {
            setFeedBackKey(PageContentKey.ORDER_DURATION_MORE_THEN_ONE_YEAR);
            result = false;
        }
        return result;
    }

    private boolean isValidAddress(String address) {
        boolean result = true;
        if (!address.matches(TEXT_WITH_NUMBERS_AND_SPACES_REGEXP)) {
            setFeedBackKey(PageContentKey.INVALID_ADDRESS);
            result = false;
        }
        if (address.length() > MAX_ADDRESS_SIZE || address.length() < MIN_ADDRESS_SIZE) {
            setFeedBackKey(PageContentKey.INVALID_ADDRESS_SIZE);
            result = false;
        }
        return result;
    }

    public boolean isValidCreditSum(String sumAsString) {
        boolean result = true;
        int sum;
        try {
            sum = Integer.parseInt(sumAsString);
        } catch (NumberFormatException e) {
            setFeedBackKey(PageContentKey.NOT_A_NUMBER);
            return false;
        }


        if (sum > MAX_SUM) {
            setFeedBackKey(PageContentKey.MORE_THEN_MAX_SUM);
            result = false;
        }
        if (sum <= 0) {
            setFeedBackKey(PageContentKey.NEGATIVE_SUM);
            result = false;
        }

        return result;
    }


    private boolean isValidDescription(String description) {
        boolean result = true;
        if (!description.matches(TEXT_WITH_NUMBERS_AND_SPACES_REGEXP)) {
            setFeedBackKey(PageContentKey.INVALID_ORDER_DESCRIPTION);
            result = false;
        }

        if (description.length() > MAX_DESCRIPTION_SIZE) {
            setFeedBackKey(PageContentKey.INVALID_ORDER_DESCRIPTION_SIZE);
            result = false;
        }
        return result;

    }

    public boolean isValidOrderForm(Date start, Date end, String address, String description,String sum) {
        return isValidOrderTime(start, end) && (end.after(start)) && (isValidAddress(address))
                && (isValidDescription(description)&&isValidCreditSum(sum));
    }


    public String getInvalidFeedback() {
        return invalidFeedback;
    }
}
