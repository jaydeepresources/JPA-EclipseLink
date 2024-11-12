package com.example;

import java.util.List;
import javax.persistence.*;

public class ActivityDAOImpl implements ActivityDAO {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("activity-app");

    @Override
    public Activity insert(Activity activity) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(activity);
        entityTransaction.commit();
        entityManager.close();
        return activity;
    }

    @Override
    public Activity findById(Long id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Activity activity = null;
        try {
            activity = entityManager.find(Activity.class, id);
        } finally {
            entityManager.close();
        }
        return activity;
    }

    @Override
    public List<Activity> findAll() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        List<Activity> activities = null;
        try {
            activities = entityManager.createQuery("SELECT a FROM Activity a", Activity.class).getResultList();
        } finally {
            entityManager.close();
        }
        return activities;
    }

    @Override
    public Activity update(Activity activity) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Activity updatedActivity = null;
        try {
            entityTransaction.begin();
            updatedActivity = entityManager.merge(activity);
            entityTransaction.commit();
        } finally {
            entityManager.close();
        }
        return updatedActivity;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Activity activity = entityManager.find(Activity.class, id);
            if (activity != null) {
                entityManager.remove(activity);
            }
            entityTransaction.commit();
        } finally {
            entityManager.close();
        }
    }
}
