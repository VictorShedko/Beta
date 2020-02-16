package by.victor.beta.controller.filterimpl;

import by.victor.beta.command.AttributeName;
import by.victor.beta.command.PagePath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter( urlPatterns =  "/jsp/login.jsp" )
public class RedirectFilter  implements Filter{

    public void init(FilterConfig fConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute(AttributeName.ROLE) == null) {
            request.getRequestDispatcher(PagePath.USER_MAIN_MENU).forward(request, response);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

}