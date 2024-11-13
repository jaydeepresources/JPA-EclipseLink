package com.example;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Runner {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("activity-app");

    public static void main(String[] args) {

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

//        User user = new User(null, "John Doe", null);
//
//        Activity activity1 = new Activity(null, "Jogging", "Morning jog", "30 minutes", user);
//        Activity activity2 = new Activity(null, "Reading", "Read a book", "1 hour", user);
//
//        entityManager.persist(user);
//        entityManager.persist(activity1);
//        entityManager.persist(activity2);

        User user = entityManager.find(User.class, 1L);
        user.getActivities().forEach(activity -> System.out.println(activity.getName()));

        entityTransaction.commit();
        entityManager.close();

    }
}