package by.victor.beta.command;

import by.victor.beta.controller.MainServlet;
import by.victor.beta.entity.Role;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestSessionContent {
    private static final Logger logger = LogManager.getLogger(MainServlet.class);
    private Map<String, Object> requestAttribute = new HashMap<>();
    private Map<String, Object> requestParameter = new HashMap<>();
    private Map<String, Object> sessionAttribute = new HashMap<>();
    private boolean invalidate;
    private File file;
    private String UPLOAD_PATH = "/tmp";//todo
    private static AtomicInteger tempNumber = new AtomicInteger(0);
    private ServletContext servletContext;

    public RequestSessionContent(HttpServletRequest request, ServletContext servletContext) {
        this.servletContext = servletContext;
        buildFile(request);
        buildRequestAttribute(request);
        buildRequestParameter(request);
        buildSessionAttribute(request.getSession());


    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Object getRequestAttribute(String attributeName) {
        return requestAttribute.get(attributeName);
    }

    public void setRequestAttribute(String name, Object value) {
        requestAttribute.put(name, value);
    }

    public Object getRequestParameter(String requestName) {
        return requestParameter.get(requestName);
    }

    public void setRequestParameter(String name, Object value) {
        requestParameter.put(name, value);
    }

    public Object getSessionAttribute(String attributeName) {
        return sessionAttribute.get(attributeName);
    }

    public void setSessionAttribute(String name, Object value) {
        sessionAttribute.put(name, value);
    }

    public boolean isInvalidate() {
        return invalidate;
    }

    public void setInvalidate(boolean invalidate) {
        this.invalidate = invalidate;
    }

    public Map<String, Object> getRequestAttribute() {
        return requestAttribute;
    }

    public void setRequestAttribute(Map<String, Object> requestAttribute) {
        this.requestAttribute = requestAttribute;
    }

    public Map<String, Object> getRequestParameter() {
        return requestParameter;
    }

    public void setRequestParameter(Map<String, Object> requestParameter) {
        this.requestParameter = requestParameter;
    }

    public Map<String, Object> getSessionAttribute() {
        return sessionAttribute;
    }

    public void setSessionAttribute(Map<String, Object> sessionAttribute) {
        this.sessionAttribute = sessionAttribute;
    }

    private void setRequestAttribute(HttpServletRequest request) {
        getRequestAttribute().forEach(request::setAttribute);
    }

    private void setSessionAttribute(HttpSession session
    ) {
        getSessionAttribute().forEach(session::setAttribute);
    }

    public void addContent(HttpServletRequest request) {
        setSessionAttribute(request.getSession());
        setRequestAttribute(request);
        if (isInvalidate()) {
            request.getSession().invalidate();
        }
    }

    private void buildSessionAttribute(HttpSession session) {
        Enumeration<String> attributeNameIterator = session.getAttributeNames();
        while (attributeNameIterator.hasMoreElements()) {
            String attributeName = attributeNameIterator.nextElement();
            setSessionAttribute(attributeName, session.getAttribute(attributeName));

        }
    }

    private void buildRequestAttribute(HttpServletRequest request) {
        Enumeration<String> attributeNameIterator = request.getAttributeNames();
        while (attributeNameIterator.hasMoreElements()) {
            String attributeName = attributeNameIterator.nextElement();
            setRequestAttribute(attributeName, request.getAttribute(attributeName));

        }
    }

    private void buildRequestParameter(HttpServletRequest request) {
        Enumeration<String> attributeNameIterator = request.getParameterNames();
        while (attributeNameIterator.hasMoreElements()) {
            String attributeName = attributeNameIterator.nextElement();
            setRequestParameter(attributeName, request.getParameter(attributeName));

        }
    }

    private void buildFile(HttpServletRequest request) {

        try {


            if (request.getContentType()!=null&&request.getContentType().startsWith("multipart/form-data")) {


                request.getParts().forEach(part -> {
                    File downloadedFile;
                    try {
                        String fileName = part.getSubmittedFileName();
                        String name = part.getName();
                        if ("file".equals(name)) {
                            String n = part.getHeader("");
                            String newFileName = "temp" + tempNumber.addAndGet(1) +
                                    fileName.substring(fileName.lastIndexOf("."));
                            downloadedFile = new File(servletContext.getRealPath("")
                                    + UPLOAD_PATH + File.separator + newFileName);
                            part.write(servletContext.getRealPath("") + UPLOAD_PATH +
                                    File.separator + newFileName);


                            file = downloadedFile;
                            logger.log(Level.DEBUG, "file is " + downloadedFile.getName());
                        }
                    } catch (IOException e) {
                        logger.log(Level.ERROR, "file read error ");
                    }
                });
            }
        } catch (IOException e) {
            logger.log(Level.DEBUG, "file io exception ", e);
        } catch (ServletException e) {
            logger.log(Level.DEBUG, "read file servlet exception ", e);
        }
    }


    public Role getRole() {
        if (getSessionAttribute(AttributeNameProvider.ROLE) == null) {
            setSessionAttribute(AttributeNameProvider.ROLE, Role.DEFAULT);
        }
        return (Role) getSessionAttribute(AttributeNameProvider.ROLE);
    }

    public void checkLocale() {
        if (getSessionAttribute(AttributeNameProvider.LOCALE) == null) {
            setSessionAttribute(AttributeNameProvider.LOCALE, "be_BY");//todo const
        }
    }
}
