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
        Activity activity1 = new Activity(null, "Jogging", "Morning jog in the park", "30 minutes");
        entityManager.persist(activity1);

        Activity activity2 = new Activity(null, "Breakfast", "Don't skip your breakfast", "15 minutes");
        entityManager.persist(activity2);
        entityTransaction.commit();


//        Fetch all activities

        String query = "SELECT a FROM Activity a";
        TypedQuery<Activity> typedQuery = entityManager.createQuery(query, Activity.class);
        List<Activity> activities = typedQuery.getResultList();

        for (Activity activity: activities)
            System.out.println(activity);

        entityManager.close();


    }
}
