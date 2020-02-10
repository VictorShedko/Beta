package by.victor.beta.tag;

import by.victor.beta.entity.Notification;
import by.victor.beta.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
@Deprecated
public class UserAsTableElementTag extends TagSupport {
    private User user;


    private void buildMessageTextWithLocale(Notification notification, Locale locale) {

    }




    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write("<tr><td class=\"table-light\"> " + user.getUsername()+ "</td>");
            pageContext.getOut().write("<td class=\"table-light\"> " + user.getStatus()+ "</td>");
            pageContext.getOut().write("<td class=\"table-light\"> " + user.getEmail()+ "</td>");
            pageContext.getOut().write("<td class=\"table-light\"> " + user.getLogin()+ "</td>");
            pageContext.getOut().write("<td class=\"table-light\"> " + user.getBalance()+ "</td>");
            pageContext.getOut().write("<td class=\"table-light\"> " + user.getRole()+ "</td>");
            pageContext.getOut().write("<td class=\"table-light\"> " +
                    "<input type=\"hidden\" name=\"command\" value=\"show_user_profile\"/>"+
                    "  <input name=\"order\" type=\"hidden\" value=\""+user.getId()+"\\>"+
                    "  <button type=\"submit\" class=\"btn btn-primary\">отменить</button>"+
                    "</td>");

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
