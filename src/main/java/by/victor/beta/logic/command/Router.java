package by.victor.beta.logic.command;

public class Router {
    private String pagePath;
    private RouterType redirectType=RouterType.FORWARD;

    public Router(String pagePath) {
        this.pagePath = pagePath;
    }

    public void setForward(){
        redirectType=RouterType.FORWARD;
    }

    public void setRedirect(){
        redirectType=RouterType.REDIRECT;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public void setRedirectType(RouterType redirectType) {
        this.redirectType = redirectType;
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouterType getRedirectType() {
        return redirectType;
    }
}
