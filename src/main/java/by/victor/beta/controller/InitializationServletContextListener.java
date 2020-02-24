package by.victor.beta.controller;

import by.victor.beta.repository.ConnectionPool;
import by.victor.beta.service.impl.OrderManager;
import by.victor.beta.service.util.ClearDeprecatedCode;
import by.victor.beta.service.util.FileManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitializationServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool provider= ConnectionPool.INSTANCE;
        OrderManager.INSTANCE.update();
        FileManager.INSTANCE.setApplicationContext(sce.getServletContext());
        ClearDeprecatedCode clearDeprecatedCode=ClearDeprecatedCode.INSTANCE;
   }

    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.INSTANCE.destroy();
    }

}
