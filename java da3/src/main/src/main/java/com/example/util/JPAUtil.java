package com.example.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    // Lazy-initialized EntityManagerFactory to avoid exception during class loading
    private static volatile EntityManagerFactory emf;

    private static EntityManagerFactory getFactory() {
        if (emf == null) {
            synchronized (JPAUtil.class) {
                if (emf == null) {
                    try {
                        emf = Persistence.createEntityManagerFactory("studentPU");
                    } catch (Throwable ex) {
                        System.err.println("Failed to create EntityManagerFactory: ");
                        ex.printStackTrace(System.err);
                        throw new RuntimeException("Could not initialize JPA EntityManagerFactory", ex);
                    }
                }
            }
        }
        return emf;
    }

    public static EntityManager getEntityManager() {
        return getFactory().createEntityManager();
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
