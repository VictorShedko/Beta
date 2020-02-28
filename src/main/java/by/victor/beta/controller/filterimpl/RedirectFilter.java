package by.victor.beta.controller.filterimpl;

import by.victor.beta.command.AttributeName;
import by.victor.beta.command.PagePath;
import by.victor.beta.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpResponse;

//@WebFilter( urlPatterns =  "/jsp/login.jsp" )
public class RedirectFilter  implements Filter{

    public void init(FilterConfig fConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse=(HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute(AttributeName.ROLE) != null&&session.getAttribute(AttributeName.ROLE)!= Role.DEFAULT) {
            httpResponse.sendRedirect(PagePath.PRG_TO_USER_MENU);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

}