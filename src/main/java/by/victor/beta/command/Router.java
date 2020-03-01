package by.victor.beta.command;

/**
 * The type Router -defines the type and route of redirection
 * default type -redirect
 * type can be changed only once
 */
public class Router {
    private String pagePath;
    private RouterType redirectType = RouterType.FORWARD;

    public Router(String pagePath) {
        this.pagePath = pagePath;
    }

    public void setRedirect() {
        redirectType = RouterType.REDIRECT;
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouterType getRedirectType() {
        return redirectType;
    }
}
