package by.victor.beta.controller.filterimpl;

import by.victor.beta.command.AttributeName;
import by.victor.beta.command.CommandType;
import by.victor.beta.entity.util.Role;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter( urlPatterns =  "/*" )
public class InvalidateUserRoleFilter implements Filter {
    private final static Logger logger= LogManager.getLogger(InvalidateUserRoleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        HttpSession session = httpServletRequest.getSession();
        Role role=(Role) session.getAttribute(AttributeName.ROLE);
        String command= httpServletRequest.getParameter(AttributeName.COMMAND);
        logger.log(Level.TRACE,"In filter role:"+role+" command:"+command);

        if(CommandType.LOGIN.name().equalsIgnoreCase(command )&&(role!=Role.DEFAULT)) {
            session.setAttribute(AttributeName.ROLE,Role.DEFAULT);
            logger.log(Level.TRACE,"login redirect role invalidation");
        }
        if(CommandType.TO_REGISTRATION.name().equalsIgnoreCase(command )&&(role!=Role.DEFAULT)) {
            session.setAttribute(AttributeName.ROLE,Role.DEFAULT);
            logger.log(Level.TRACE,"registration redirect role invalidation");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
