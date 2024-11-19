package com.example;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class ActivityRunner {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("activity-app");

    public static void main(String[] args) {

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

//        Save some activities
//        Activity activity1 = new Activity(null, "Jogging", "Morning jog in the park", "30 minutes");
//        entityManager.persist(activity1);
//
//        Activity activity2 = new Activity(null, "Breakfast", "Don't skip your breakfast", "15 minutes");
//        entityManager.persist(activity2);
//
//        entityTransaction.commit();

//        Merge object

//        Activity activity2 = entityManager.find(Activity.class, 1L);
//        activity2.setDuration("20 minutes");
//        activity2 = entityManager.merge(activity2);
//        entityTransaction.commit();
//        System.out.println(activity2);

//        Activity activity = new Activity(null, "Breakfast", "Don't skip your breakfast", "25 minutes");
//        activity = entityManager.merge(activity);
//        entityTransaction.commit();
//        System.out.println(activity);

//        Delete an activity

//        entityManager.remove(entityManager.find(Activity.class, 3L));
//        entityTransaction.commit();
//        Fetch all activities

//        String query = "SELECT a FROM Activity a";
//        TypedQuery<Activity> typedQuery = entityManager.createQuery(query, Activity.class);
//        List<Activity> activities = typedQuery.getResultList();
//
//        for (Activity activity: activities)
//            System.out.println(activity);

        entityManager.close();


    }
}
