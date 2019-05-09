package be.pxl.ja2.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.util.Enumeration;

@WebListener
public class EntityManagerUtil implements ServletContextListener {
    private static EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        emf = Persistence.createEntityManagerFactory("studentDB");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        emf.close();
        // https://www.ralph-schuster.eu/2014/07/09/solution-to-tomcat-cant-stop-an-abandoned-connection-cleanup-thread/
        try {
            com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown();
        } catch (Throwable t) {
            // TODO log exception
        }
        // This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks
        Enumeration<Driver> drivers = java.sql.DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            java.sql.Driver driver = drivers.nextElement();
            try {
                java.sql.DriverManager.deregisterDriver(driver);
            } catch (Throwable t) {
                // TODO log exception
            }
        }
    }

    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }
        return emf.createEntityManager();
    }
}
