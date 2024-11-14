package com.example.pessimistic.runner;

import com.example.pessimistic.entity.PessimisticProductLock;
import com.example.pessimistic.manager.PessimisticProductLockManager;
import com.example.util.JPAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        PessimisticProductLockManager manager = new PessimisticProductLockManager();

        // Create and add a product
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        PessimisticProductLock productLock = new PessimisticProductLock(null, "Laptop", "Electronics", 1200.00, 10.0, 500, 4.5, null);
        entityManager.persist(productLock);
        transaction.commit();
        entityManager.close();

        Long productId = productLock.getId();

        // Simulate concurrent updates
        Thread user1 = new Thread(() -> manager.updatePessimisticProductLock(productId, "Gaming Laptop", 1300.00));
        Thread user2 = new Thread(() -> manager.updatePessimisticProductLock(productId, "Ultrabook", 1400.00));

        user1.start();
        user2.start();

        try {
            user1.join();
            user2.join();
        } catch (InterruptedException e) {
            logger.error("Thread interrupted.", e);
        }

        // Print final product details
        PessimisticProductLock updatedProductLock = manager.findPessimisticProductLockById(productId);
        logger.info("Final product details: {}", updatedProductLock);

        // Close the entity manager factory
        JPAUtil.close();
    }
}
