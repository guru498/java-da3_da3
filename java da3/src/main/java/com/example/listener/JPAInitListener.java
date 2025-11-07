package com.example.listener;

import com.example.util.JPAUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.persistence.EntityManager;

@WebListener
public class JPAInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Force initialization of the EntityManagerFactory and validate that we can obtain an EntityManager
            EntityManager em = JPAUtil.getEntityManager();
            if (em != null) {
                em.close();
            }
            System.out.println("JPA EntityManagerFactory initialized successfully.");
        } catch (Throwable t) {
            // Log to stderr so Jetty/Maven console shows this prominently
            System.err.println("Failed to initialize JPA EntityManagerFactory on startup:");
            t.printStackTrace(System.err);
            // Rethrow as RuntimeException will prevent successful startup — this is desirable so issues surface early
            throw new RuntimeException("JPA initialization failure", t);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanly close the factory when webapp stops
        try {
            JPAUtil.close();
            System.out.println("JPA EntityManagerFactory closed.");
        } catch (Throwable t) {
            System.err.println("Error while closing JPA EntityManagerFactory:");
            t.printStackTrace(System.err);
        }
    }
}
