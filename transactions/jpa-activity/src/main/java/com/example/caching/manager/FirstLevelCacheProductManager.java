package com.example.caching.manager;

import com.example.caching.entity.FirstLevelCacheProduct;
import com.example.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstLevelCacheProductManager {

    private static final Logger logger = LoggerFactory.getLogger(FirstLevelCacheProductManager.class);

    public void addProduct(FirstLevelCacheProduct product) {
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

    public FirstLevelCacheProduct findProductById(Long productId) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        FirstLevelCacheProduct product = null;

        try {
            product = entityManager.find(FirstLevelCacheProduct.class, productId);
            logger.info("Found product by ID {}: {}", productId, product);
        } finally {
            entityManager.close();
        }

        return product;
    }

    public void demonstrateFirstLevelCache() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // Find product by ID for the first time
            FirstLevelCacheProduct product1 = entityManager.find(FirstLevelCacheProduct.class, 1L);
            logger.info("First query result: {}", product1);

            // Find product by ID again within the same transaction
            FirstLevelCacheProduct product2 = entityManager.find(FirstLevelCacheProduct.class, 1L);
            logger.info("Second query result: {}", product2);

            // Check if both references point to the same object in the first-level cache
            logger.info("Are both references the same? {}", product1 == product2);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("An error occurred, rolling back transaction.", e);
        } finally {
            entityManager.close();
        }
    }
}
