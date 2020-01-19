package by.victor.beta.logic.command;

import java.util.HashMap;
import java.util.Map;

public class RequestSessionContent {
    private Map<String,Object> requestAttribute=new HashMap<>();
    private Map<String,Object> requestParameter=new HashMap<>();
    private Map<String,Object> sessionAttribute=new HashMap<>();
    private boolean invalidate;

    public Object getRequestAttribute(String attributeName) {
        return requestAttribute.get(attributeName);
    }

    public void setRequestAttribute(String name, Object value) {
        requestAttribute.put(name,value) ;
    }

    public Object getRequestParameter(String requestName) {
        return requestParameter.get(requestName);
    }

    public void setRequestParameter(String name, Object value) {
        requestParameter.put(name,value);
    }

    public Object getSessionAttribute(String attributeName) {
        return sessionAttribute.get(attributeName);
    }

    public void setSessionAttribute(String name, Object value) {
        sessionAttribute.put(name,value);
    }

    public boolean isInvalidate() {
        return invalidate;
    }

    public void setInvalidate(boolean invalidate) {
        this.invalidate = invalidate;
    }
}
