package by.victor.beta.controller.filterimpl;


import by.victor.beta.command.AttributeName;
import by.victor.beta.command.CommandType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter( urlPatterns =  "/*" )
public class BackButtonFilter implements Filter {
    private static final String LOGIN_PAGE="login.jsp";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;//todo norm
        HttpSession httpSession=httpServletRequest.getSession();
        String last=(String) httpSession.getAttribute(AttributeName.LAST_PAGE);
        String command=httpServletRequest.getParameter(AttributeName.COMMAND);
        if(!((LOGIN_PAGE.equalsIgnoreCase(last))&&CommandType.TO_USER_MENU.toString().equalsIgnoreCase(command))){
            httpSession.setAttribute(AttributeName.LAST_PAGE,httpServletRequest.getRequestURI());
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
