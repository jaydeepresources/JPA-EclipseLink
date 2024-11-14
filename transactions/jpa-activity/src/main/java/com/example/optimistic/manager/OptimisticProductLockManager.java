package com.example.optimistic.manager;

import com.example.optimistic.entity.OptimisticProductLock;
import com.example.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptimisticProductLockManager {

    private static final Logger logger = LoggerFactory.getLogger(OptimisticProductLockManager.class);

    public void updateOptimisticProductLock(Long productId, String newName, Double newPrice) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            OptimisticProductLock productLock = entityManager.find(OptimisticProductLock.class, productId);
            if (productLock != null) {
                productLock.setName(newName);
                productLock.setPrice(newPrice);
                entityManager.persist(productLock);
                transaction.commit();
                logger.info("Updated product: {}", productLock);
            } else {
                logger.warn("Product with ID {} not found.", productId);
            }
        } catch (OptimisticLockException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Optimistic lock exception occurred, rolling back transaction.", e);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("An error occurred, rolling back transaction.", e);
        } finally {
            entityManager.close();
        }
    }

    public void addOptimisticProductLock(OptimisticProductLock productLock) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(productLock);
            transaction.commit();
            logger.info("Added product: {}", productLock);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Failed to add product, rolling back.", e);
        } finally {
            entityManager.close();
        }
    }

    public OptimisticProductLock findOptimisticProductLockById(Long productId) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        OptimisticProductLock productLock = null;

        try {
            transaction.begin();
            productLock = entityManager.find(OptimisticProductLock.class, productId);
            transaction.commit();
            logger.info("Found product by ID {}: {}", productId, productLock);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("An error occurred while finding product, rolling back transaction.", e);
        } finally {
            entityManager.close();
        }

        return productLock;
    }
}
