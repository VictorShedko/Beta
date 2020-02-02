package by.victor.beta.command;

public class Router {
    private String pagePath;
    private RouterType redirectType = RouterType.FORWARD;

    public Router(String pagePath) {
        this.pagePath = pagePath;
    }

    public void setRedirect() {
        redirectType = RouterType.REDIRECT;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouterType getRedirectType() {
        return redirectType;
    }
}
