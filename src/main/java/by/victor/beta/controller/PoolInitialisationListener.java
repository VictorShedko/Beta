package by.victor.beta.controller;

import by.victor.beta.logic.service.ServiceFacade;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PoolInitialisationListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServiceFacade.instance.init();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        ServiceFacade.instance.destroy();
    }

}
