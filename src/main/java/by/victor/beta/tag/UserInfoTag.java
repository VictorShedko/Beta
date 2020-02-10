package by.victor.beta.tag;
import by.victor.beta.command.AttributeNameProvider;
import by.victor.beta.entity.Role;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.JspWriter;
//import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class UserInfoTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        try {
           Role userRole=   (Role) pageContext.getSession().
                   getAttribute(AttributeNameProvider.ROLE);
           pageContext.getOut().write("<p>" +userRole+ "<p/>");

            String locale=   (String) pageContext.getSession().
                    getAttribute(AttributeNameProvider.LOCALE);
            pageContext.getOut().write("<p>" +locale+ "<p/>");

            String username=   (String) pageContext.getSession().
                    getAttribute(AttributeNameProvider.USERNAME);
            pageContext.getOut().write("<p>" +username+ "<p/>");
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
