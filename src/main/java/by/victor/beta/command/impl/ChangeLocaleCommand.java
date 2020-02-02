package by.victor.beta.command.impl;

import by.victor.beta.command.*;
import by.victor.beta.entity.SupportedLocale;

public class ChangeLocaleCommand implements AbstractCommand {
    @Override
    public Router execute(RequestSessionContent content) {
        String localeString = (String) content.getSessionAttribute(AttributeNameProvider.LOCALE);
        SupportedLocale oldLocale = SupportedLocale.fromValue(localeString);
        SupportedLocale newLocale;
        if (oldLocale.ordinal() == 0) {
             newLocale = SupportedLocale.EN;
        } else {
             newLocale = SupportedLocale.BE;
        }

        content.setSessionAttribute(AttributeNameProvider.LOCALE,newLocale.getLocaleName());
        return new Router(PagePathProvider.USER_MAIN_PAGE);
    }
}
