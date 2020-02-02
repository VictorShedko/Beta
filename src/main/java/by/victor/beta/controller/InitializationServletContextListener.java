package by.victor.beta.controller;

import by.victor.beta.repository.ConnectionProvider;
import by.victor.beta.service.ServiceFacade;
import by.victor.beta.service.orderservice.OrderManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitializationServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ConnectionProvider.instance.ordinal();
        OrderManager.instance.update();



    }

    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionProvider.instance.destroy();
    }

}
