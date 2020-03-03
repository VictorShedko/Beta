package by.victor.beta.command;

import by.victor.beta.controller.MainServlet;
import by.victor.beta.entity.util.Role;
import by.victor.beta.entity.User;
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


/**
 * The type Request session content -contains session attributes, request parameters.
 */
public class RequestSessionContent {
    private static final Logger logger = LogManager.getLogger(MainServlet.class);
    private Map<String, Object> requestAttribute = new HashMap<>();
    private Map<String, Object> requestParameter = new HashMap<>();
    private Map<String, Object> sessionAttribute = new HashMap<>();
    private boolean invalidate;
    private static final String TEMP_FOLDER_NAME="temp";
    private File file;
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

    /**
     * Gets request attribute.
     *
     * @param attributeName the attribute name
     * @return the request attribute
     */
    public Object getRequestAttribute(String attributeName) {
        return requestAttribute.get(attributeName);
    }

    /**
     * Sets request attribute.
     *
     * @param name  the name
     * @param value the value
     */
    public void setRequestAttribute(String name, Object value) {
        requestAttribute.put(name, value);
    }

    /**
     * Gets request parameter.
     *
     * @param requestName the request name
     * @return the request parameter
     */
    public Object getRequestParameter(String requestName) {
        return requestParameter.get(requestName);
    }

    /**
     * Sets request parameter.
     *
     * @param name  the name
     * @param value the value
     */
    public void setRequestParameter(String name, Object value) {
        requestParameter.put(name, value);
    }

    /**
     * Gets session attribute.
     *
     * @param attributeName the attribute name
     * @return the session attribute
     */
    public Object getSessionAttribute(String attributeName) {
        return sessionAttribute.get(attributeName);
    }

    /**
     * Sets session attribute.
     *
     * @param name  the name
     * @param value the value
     */
    public void setSessionAttribute(String name, Object value) {
        sessionAttribute.put(name, value);
    }


    public boolean isInvalidate() {
        return invalidate;
    }

    /**
     * set if need invalidate session.
     *
     * @param invalidate the invalidate
     */
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

    private void setSessionAttribute(HttpSession session) {
        getSessionAttribute().forEach(session::setAttribute);
    }

    /**
     * Add all parameters which this object contains to session and request.
     *
     * @param request the request
     */
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

    /**
     * build file from request if it present.
     * save new file as tempN -where n is sequence number of created file
     * by upload path witch defined in ApplicationParameter class
     */
    private void buildFile(HttpServletRequest request) {

        try {
            if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
                request.getParts().forEach(part -> {
                    File downloadedFile;
                    try {
                        String fileName = part.getSubmittedFileName();
                        String name = part.getName();
                        if ("file".equals(name)) {

                            String newFileName = TEMP_FOLDER_NAME+ tempNumber.addAndGet(1) +
                                    fileName.substring(fileName.lastIndexOf("."));
                            downloadedFile = new File(
                                    ApplicationParameter.FILE_UPLOAD_PATH + File.separator + newFileName);
                            part.write(ApplicationParameter.FILE_UPLOAD_PATH +
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

    /**
     * Gets role.
     *
     * @return the role
     */
    @Deprecated
    public Role getRole() {
        if (getSessionAttribute(AttributeName.ROLE) == null) {
            setSessionAttribute(AttributeName.ROLE, Role.DEFAULT);
        }
        return (Role) getSessionAttribute(AttributeName.ROLE);
    }

    /**
     * Check locale.
     */
    @Deprecated
    public void checkLocale() {
        if (getSessionAttribute(AttributeName.LOCALE) == null) {
            setSessionAttribute(AttributeName.LOCALE, PageContentKey.RU_LOCALE);
        }
    }

    /**
     * Add user information to session in correct form.
     *
     * @param user the user
     */
    public void addUserToSession(User user){
        setSessionAttribute(AttributeName.STATUS, user.getStatus());
        setSessionAttribute(AttributeName.USERNAME, user.getUsername());
        setSessionAttribute(AttributeName.ROLE, user.getRole());
        setSessionAttribute(AttributeName.BALANCE, user.getBalance());
        if (user.getPhotoPath() != null) {
            setSessionAttribute(AttributeName.PHOTO_PATH, user.getPhotoPath());
        } else {
            setSessionAttribute(AttributeName.PHOTO_PATH,
                    AttributeName.DEFAULT_PHOTO_PATH);
        }
    }
}
