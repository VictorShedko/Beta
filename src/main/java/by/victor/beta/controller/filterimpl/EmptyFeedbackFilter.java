package by.victor.beta.controller.filterimpl;

import by.victor.beta.command.AttributeName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Deprecated
@WebFilter( urlPatterns =  "/*" )
public class EmptyFeedbackFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session=httpServletRequest.getSession();
        if(session.getAttribute(AttributeName.FEEDBACK)==null){
            session.setAttribute(AttributeName.FEEDBACK, "feedback.empty");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
