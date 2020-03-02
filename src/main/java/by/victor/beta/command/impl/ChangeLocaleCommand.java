package by.victor.beta.command.impl;

import by.victor.beta.command.*;
import by.victor.beta.entity.SupportedLocale;

public class ChangeLocaleCommand implements Command {
    @Override
    public Router execute(RequestSessionContent content) {
        String localeString = (String) content.getSessionAttribute(AttributeName.LOCALE);
        SupportedLocale oldLocale = SupportedLocale.fromValue(localeString);
        SupportedLocale newLocale;
        if (oldLocale==SupportedLocale.BE) {
             newLocale = SupportedLocale.EN;
        } else {
             newLocale = SupportedLocale.BE;
        }
        content.setSessionAttribute(AttributeName.LOCALE,newLocale.getLocaleName());
        return new Router(PagePath.USER_MAIN_MENU);
    }
}
