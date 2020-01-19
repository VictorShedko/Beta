package by.victor.beta.logic.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;


public class Transmitter {
    private void setRequestAttribute(HttpServletRequest request, HttpSession session,
                                     RequestSessionContent content) {

    }

    private void setRequestParameter(HttpServletRequest request, HttpSession session,
                                     RequestSessionContent content) {

    }

    private void setSessionAttribute(HttpServletRequest request, HttpSession session,
                                     RequestSessionContent content) {

    }

    public void addContent(HttpServletRequest request, HttpSession session,
                           RequestSessionContent content) {
        setSessionAttribute(request,session,content);
        setRequestParameter(request,session,content);
        setRequestAttribute(request,session,content);
        if(content.isInvalidate()){
            session.invalidate();
        }
    }

    private void buildSessionAttribute(HttpServletRequest request, HttpSession session,
                                       RequestSessionContent content, String attributeName) {
        if (session.getAttribute(attributeName) != null) {
            content.setSessionAttribute(attributeName, session.getAttribute(attributeName));
        }
    }

    private void buildRequestAttribute(HttpServletRequest request, HttpSession session,
                                       RequestSessionContent content) {
        Enumeration<String> attributeNameIterator= request.getAttributeNames();
        while (attributeNameIterator.hasMoreElements()) {
            String attributeName=attributeNameIterator.nextElement();
            if (request.getAttribute(attributeName) != null) {
                content.setRequestAttribute(attributeName, request.getParameter(attributeName));
            }
        }
    }

    private void buildRequestParameter(HttpServletRequest request, HttpSession session,
                                       RequestSessionContent content, String parameterName) {
        if (request.getParameter(parameterName) != null) {
            content.setRequestParameter(parameterName, request.getParameter(parameterName));
        }

    }


    public RequestSessionContent buildContent(HttpServletRequest request, HttpSession session) {
        RequestSessionContent content = new RequestSessionContent();
      buildRequestAttribute(request,session,content);
        AttributeNameProvider.getRequestParameterList()
                .forEach(t->buildRequestParameter(request,session,content,t));;
        AttributeNameProvider.getSessionAttributeList()
                .forEach(t->buildSessionAttribute(request,session,content,t));;

        return content;
    }


}
