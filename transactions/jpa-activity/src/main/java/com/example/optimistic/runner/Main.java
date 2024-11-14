package com.example.optimistic.runner;

import com.example.optimistic.entity.OptimisticProductLock;
import com.example.optimistic.manager.OptimisticProductLockManager;
import com.example.util.JPAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        OptimisticProductLockManager manager = new OptimisticProductLockManager();

        // Create and add a product
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        OptimisticProductLock productLock = new OptimisticProductLock(null, "Laptop", "Electronics", 1200.00, 10.0, 500, 4.5, null);
        entityManager.persist(productLock);
        transaction.commit();
        entityManager.close();

        Long productId = productLock.getId();

        // Simulate concurrent updates
        Thread user1 = new Thread(() -> manager.updateOptimisticProductLock(productId, "Gaming Laptop", 1300.00));
        Thread user2 = new Thread(() -> manager.updateOptimisticProductLock(productId, "Ultrabook", 1400.00));

        user1.start();
        user2.start();

        try {
            user1.join();
            user2.join();
        } catch (InterruptedException e) {
            logger.error("Thread interrupted.", e);
        }

        // Print final product details
        OptimisticProductLock updatedProductLock = manager.findOptimisticProductLockById(productId);
        logger.info("Final product details: {}", updatedProductLock);

        // Close the entity manager factory
        JPAUtil.close();
    }
}
