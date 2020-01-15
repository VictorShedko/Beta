package by.victor.beta.controller;

import by.victor.beta.logic.entity.Role;

import javax.servlet.http.HttpSession;

public class SessionAction {

    public Role getRole(HttpSession session){
        if (session.getAttribute("role") == null) {
            session.setAttribute("role", Role.DEFAULT);
        }
        return (Role) session.getAttribute("role");
    }

    public void setRole(HttpSession session,Role role){
        session.setAttribute("role",role);
    }
}
