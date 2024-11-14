package com.example.pessimistic.manager;

import com.example.pessimistic.entity.PessimisticProductLock;
import com.example.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PessimisticProductLockManager {

    private static final Logger logger = LoggerFactory.getLogger(PessimisticProductLockManager.class);

    public void updatePessimisticProductLock(Long productId, String newName, Double newPrice) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            PessimisticProductLock productLock = entityManager.find(PessimisticProductLock.class, productId, LockModeType.PESSIMISTIC_WRITE);
            if (productLock != null) {
                productLock.setName(newName);
                productLock.setPrice(newPrice);
                entityManager.persist(productLock);
                transaction.commit();
                logger.info("Updated product: {}", productLock);
            } else {
                logger.warn("Product with ID {} not found.", productId);
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("An error occurred, rolling back transaction.", e);
        } finally {
            entityManager.close();
        }
    }

    public void addPessimisticProductLock(PessimisticProductLock productLock) {
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

    public PessimisticProductLock findPessimisticProductLockById(Long productId) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        PessimisticProductLock productLock = null;

        try {
            transaction.begin();
            productLock = entityManager.find(PessimisticProductLock.class, productId, LockModeType.PESSIMISTIC_READ);
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
