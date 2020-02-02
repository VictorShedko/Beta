package by.victor.beta.tag;

import by.victor.beta.command.AttributeNameProvider;
import by.victor.beta.entity.Notify;
import by.victor.beta.entity.NotifyType;
import by.victor.beta.service.notifyservice.NotifyMessageBuilder;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class NotifyTag extends TagSupport {

    private String messageText;
    private Notify notify;


    private void buildMessageTextWithLocale(Notify notify, Locale locale) {
        NotifyMessageBuilder builder = new NotifyMessageBuilder();
        messageText = builder.buildByPatter(notify.getValues(), notify.getType(), locale);


    }

    private Locale getLocale(String localeAsString) {
        Locale locale = new Locale(localeAsString);
        return locale;
    }


    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write("<hr/>" + notify.getDate() + "<hr/>");
            Locale locale = getLocale((String) pageContext.getSession().
                    getAttribute(AttributeNameProvider.LOCALE));
            buildMessageTextWithLocale(notify, locale);
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

    public Notify getNotify() {
        return notify;
    }

    public void setNotify(Notify notify) {
        this.notify = notify;
    }
}
