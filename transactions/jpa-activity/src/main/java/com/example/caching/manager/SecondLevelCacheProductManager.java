package com.example.caching.manager;

import com.example.caching.entity.SecondLevelCacheProduct;
import com.example.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;

public class SecondLevelCacheProductManager {

    private static final Logger logger = LoggerFactory.getLogger(SecondLevelCacheProductManager.class);

    public void addProduct(SecondLevelCacheProduct product) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(product);
            transaction.commit();
            logger.info("Added product: {}", product);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Failed to add product, rolling back transaction.", e);
        } finally {
            entityManager.close();
        }
    }

    public SecondLevelCacheProduct findProductById(Long productId) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        SecondLevelCacheProduct product = null;

        try {
            product = entityManager.find(SecondLevelCacheProduct.class, productId);
            logger.info("Found product by ID {}: {}", productId, product);
        } finally {
            entityManager.close();
        }

        return product;
    }

    public void demonstrateSecondLevelCache() {
        EntityManager entityManager1 = JPAUtil.getEntityManager();
        EntityManager entityManager2 = JPAUtil.getEntityManager();
        EntityTransaction transaction1 = entityManager1.getTransaction();
        EntityTransaction transaction2 = entityManager2.getTransaction();

        try {
            // Use first EntityManager instance
            transaction1.begin();
            SecondLevelCacheProduct product1 = entityManager1.find(SecondLevelCacheProduct.class, 1L);
            logger.info("First query result from EntityManager1: {}", product1);
            transaction1.commit();
            entityManager1.close();

            // Use second EntityManager instance
            transaction2.begin();
            SecondLevelCacheProduct product2 = entityManager2.find(SecondLevelCacheProduct.class, 1L);
            logger.info("First query result from EntityManager2: {}", product2);
            transaction2.commit();
            entityManager2.close();
        } catch (Exception e) {
            if (transaction1.isActive()) {
                transaction1.rollback();
            }
            if (transaction2.isActive()) {
                transaction2.rollback();
            }
            logger.error("An error occurred, rolling back transaction.", e);
        }
    }

    public void printCacheContents() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        javax.persistence.Cache cache = entityManager.getEntityManagerFactory().getCache();

        if (cache.contains(SecondLevelCacheProduct.class, 1L)) {
            logger.info("Cache contains entity with ID 1");
            SecondLevelCacheProduct product = entityManager.find(SecondLevelCacheProduct.class, 1L);
            logger.info("Cached entity: {}", product);
        } else {
            logger.info("No cache content found for SecondLevelCacheProduct.");
        }
        entityManager.close();
    }
}