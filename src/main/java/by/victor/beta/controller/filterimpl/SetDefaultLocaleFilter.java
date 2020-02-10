package by.victor.beta.controller.filterimpl;

import by.victor.beta.command.AttributeNameProvider;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter( urlPatterns = { "/*" },
        initParams = { @WebInitParam(name = "locale", value = "be_BY") })
public class SetDefaultLocaleFilter implements Filter {
    private String locale;

    public void init(FilterConfig fConfig) throws ServletException {
        locale = fConfig.getInitParameter("locale");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        if (locale != null && session.getAttribute(AttributeNameProvider.LOCALE) == null) {
            session.setAttribute(AttributeNameProvider.LOCALE, locale);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
        locale = null;
    }
}