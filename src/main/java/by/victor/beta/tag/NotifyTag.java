package by.victor.beta.tag;

import by.victor.beta.command.AttributeNameProvider;
import by.victor.beta.entity.Notification;
import by.victor.beta.service.impl.NotifyMessageBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.util.Locale;

public class NotifyTag extends TagSupport {
    private final static Logger logger= LogManager.getLogger(NotifyTag.class);
    private String messageText;
    private Notification notification;


    private void buildMessageTextWithLocale(Notification notification, Locale locale) {

        NotifyMessageBuilder builder = new NotifyMessageBuilder();

        messageText = builder.buildByPatter(notification.getValues(), notification.getType(), locale);


    }

    private Locale getLocale(String localeAsString) {
        Locale locale = new Locale(localeAsString);
        return locale;
    }


    @Override
    public int doStartTag() throws JspException {
        try {

            pageContext.getOut().write("<hr/>" + notification.getDate() + "<hr/>");
            Locale locale = getLocale((String) pageContext.getSession().
                    getAttribute(AttributeNameProvider.LOCALE));
            logger.log(Level.DEBUG,"notify tag"+ notification +" locale:"+locale);
            buildMessageTextWithLocale(notification, locale);
            pageContext.getOut().write("<hr/>" + messageText + "<hr/>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
