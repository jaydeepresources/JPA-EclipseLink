package com.example.processor;

import com.example.util.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Main {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Create and persist a ProcessorUser
            ProcessorUser user = new ProcessorUser();
            user.setName("Jane Doe");
            user.setEmail("jane.doe@example.com");

            em.persist(user);

            tx.commit();

            // Retrieve and update the ProcessorUser
            tx.begin();
            ProcessorUser retrievedUser = em.find(ProcessorUser.class, user.getId());
            retrievedUser.setEmail("jane.updated@example.com");

            em.merge(retrievedUser);

            tx.commit();

            // Retrieve and print the updated ProcessorUser
            ProcessorUser updatedUser = em.find(ProcessorUser.class, user.getId());
            System.out.println("Updated ProcessorUser: " + updatedUser);

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            JPAUtil.close();
        }
    }
}
