package by.victor.beta.controller;

import by.victor.beta.logic.command.AttributeNameProvider;
import by.victor.beta.logic.command.RequestSessionContent;
import by.victor.beta.logic.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionAction {

    public Role getRole(HttpSession session){
        if (session.getAttribute(AttributeNameProvider.ROLE) == null) {
            session.setAttribute(AttributeNameProvider.ROLE, Role.DEFAULT);
        }
        return (Role) session.getAttribute(AttributeNameProvider.ROLE);
    }

    public void setRole(HttpSession session,Role role){
        session.setAttribute("role",role);
    }
    public void setUsername(HttpSession session,String username){
        session.setAttribute("username",username);
    }
    public String getUsername(HttpSession session){
      return (String) session.getAttribute("username") ;

    }


    public Object getAttribute(HttpSession session,String attribute){
        return session.getAttribute(attribute);
    }
}
