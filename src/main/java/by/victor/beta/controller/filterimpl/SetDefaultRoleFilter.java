package by.victor.beta.controller.filterimpl;

import by.victor.beta.command.AttributeName;
import by.victor.beta.entity.util.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * if role is null,filter set role default.
 */
@WebFilter( urlPatterns =  "/*" )
public class SetDefaultRoleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute(AttributeName.ROLE)==null){
            session.setAttribute(AttributeName.ROLE, Role.DEFAULT);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
