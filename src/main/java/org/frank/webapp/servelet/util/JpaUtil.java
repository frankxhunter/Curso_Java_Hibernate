package org.frank.webapp.servelet.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory emf = buildEntityManagerFactori();
    
        private static EntityManagerFactory buildEntityManagerFactori() {
            return Persistence.createEntityManagerFactory("ejemplo");
        }

        public static EntityManager getEntityManager() {
            return emf.createEntityManager();
        }

    
}
