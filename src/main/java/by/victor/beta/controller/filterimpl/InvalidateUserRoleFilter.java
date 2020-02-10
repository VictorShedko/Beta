package by.victor.beta.controller.filterimpl;

import by.victor.beta.command.AttributeNameProvider;
import by.victor.beta.command.CommandType;
import by.victor.beta.entity.Role;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
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
        Role role=(Role) session.getAttribute(AttributeNameProvider.ROLE);
        String command= httpServletRequest.getParameter(AttributeNameProvider.COMMAND);
        logger.log(Level.DEBUG,"In filter role:"+role+" command:"+command);
        if(CommandType.LOGIN.name().equalsIgnoreCase(command )&&(role!=Role.DEFAULT)) {
            session.setAttribute(AttributeNameProvider.ROLE,Role.DEFAULT);
            logger.log(Level.DEBUG,"login redirect role invalidation");
        }
        if(CommandType.REGISTER.name().equalsIgnoreCase(command )&&(role!=Role.DEFAULT)) {
            session.setAttribute(AttributeNameProvider.ROLE,Role.DEFAULT);
            logger.log(Level.DEBUG,"registration redirect role invalidation");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
