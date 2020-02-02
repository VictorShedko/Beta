package by.victor.beta.tag;
import by.victor.beta.command.AttributeNameProvider;
import by.victor.beta.entity.Notify;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Locale;
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.JspWriter;
//import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class UserInfoTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        try {
           String userRole=   (String) pageContext.getSession().
                   getAttribute(AttributeNameProvider.LOCALE);
           pageContext.getOut().write("<hr/>" +userRole+ "<hr/>");

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }




}
