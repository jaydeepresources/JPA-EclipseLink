package com.example.joins;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DataInserter {
    public static void main(String[] args) {
        EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("activity-app");
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

                try {
                    entityTransaction.begin();

                    // Create and persist the first user with activities
                    User user1 = new User(null, "John Doe", null);
                    entityManager.persist(user1);
                    UserActivity userActivity1 = new UserActivity(null, "Running", "Morning jog in the park", "30 minutes", user1);
                    UserActivity userActivity2 = new UserActivity(null, "Reading", "Read a chapter of a book", "1 hour", user1);
                    UserActivity userActivity3 = new UserActivity(null, "Coding", "Work on a side project", "2 hours", user1);
                    UserActivity userActivity4 = new UserActivity(null, "Cooking", "Prepare dinner", "1.5 hours", user1);
                    UserActivity userActivity5 = new UserActivity(null, "Meditation", "Evening meditation session", "20 minutes", user1);
                    entityManager.persist(userActivity1);
                    entityManager.persist(userActivity2);
                    entityManager.persist(userActivity3);
                    entityManager.persist(userActivity4);
                    entityManager.persist(userActivity5);

                    // Create and persist the second user with activities
                    User user2 = new User(null, "Jane Smith", null);
                    entityManager.persist(user2);
                    UserActivity userActivity6 = new UserActivity(null, "Yoga", "Morning yoga session", "1 hour", user2);
                    UserActivity userActivity7 = new UserActivity(null, "Shopping", "Grocery shopping", "2 hours", user2);
                    UserActivity userActivity8 = new UserActivity(null, "Writing", "Journal writing", "45 minutes", user2);
                    UserActivity userActivity9 = new UserActivity(null, "Gardening", "Water the plants", "30 minutes", user2);
                    UserActivity userActivity10 = new UserActivity(null, "Movie", "Watch a movie", "2 hours", user2);
                    entityManager.persist(userActivity6);
                    entityManager.persist(userActivity7);
                    entityManager.persist(userActivity8);
                    entityManager.persist(userActivity9);
                    entityManager.persist(userActivity10);

                    // Create and persist the third user with activities
                    User user3 = new User(null, "Alice Johnson", null);
                    entityManager.persist(user3);
                    UserActivity userActivity11 = new UserActivity(null, "Cycling", "Afternoon cycling", "1 hour", user3);
                    UserActivity userActivity12 = new UserActivity(null, "Painting", "Work on a painting", "3 hours", user3);
                    UserActivity userActivity13 = new UserActivity(null, "Reading", "Read a magazine", "1 hour", user3);
                    UserActivity userActivity14 = new UserActivity(null, "Cooking", "Bake cookies", "1.5 hours", user3);
                    UserActivity userActivity15 = new UserActivity(null, "Walking", "Evening walk", "45 minutes", user3);
                    entityManager.persist(userActivity11);
                    entityManager.persist(userActivity12);
                    entityManager.persist(userActivity13);
                    entityManager.persist(userActivity14);
                    entityManager.persist(userActivity15);

                    // Create and persist the fourth user with activities
                    User user4 = new User(null, "Robert Brown", null);
                    entityManager.persist(user4);
                    UserActivity userActivity16 = new UserActivity(null, "Gym", "Morning gym workout", "1.5 hours", user4);
                    UserActivity userActivity17 = new UserActivity(null, "Meeting", "Business meeting", "2 hours", user4);
                    UserActivity userActivity18 = new UserActivity(null, "Reading", "Read a business book", "1 hour", user4);
                    UserActivity userActivity19 = new UserActivity(null, "Cooking", "Prepare lunch", "1 hour", user4);
                    UserActivity userActivity20 = new UserActivity(null, "TV", "Watch TV", "2 hours", user4);
                    entityManager.persist(userActivity16);
                    entityManager.persist(userActivity17);
                    entityManager.persist(userActivity18);
                    entityManager.persist(userActivity19);
                    entityManager.persist(userActivity20);

                    // Create and persist the fifth user with activities
                    User user5 = new User(null, "Emily Davis", null);
                    entityManager.persist(user5);
                    UserActivity userActivity21 = new UserActivity(null, "Swimming", "Morning swim", "1 hour", user5);
                    UserActivity userActivity22 = new UserActivity(null, "Reading", "Read a novel", "2 hours", user5);
                    UserActivity userActivity23 = new UserActivity(null, "Writing", "Work on a blog post", "1 hour", user5);
                    UserActivity userActivity24 = new UserActivity(null, "Cooking", "Prepare breakfast", "1 hour", user5);
                    UserActivity userActivity25 = new UserActivity(null, "Exercise", "Home workout", "45 minutes", user5);
                    entityManager.persist(userActivity21);
                    entityManager.persist(userActivity22);
                    entityManager.persist(userActivity23);
                    entityManager.persist(userActivity24);
                    entityManager.persist(userActivity25);

                    entityTransaction.commit();
                    System.out.println("Inserted 5 users, each having 5 activities.");
                } catch (Exception e) {
                    if (entityTransaction.isActive()) {
                        entityTransaction.rollback();
                    }
                    e.printStackTrace();
                } finally {
                    entityManager.close();
                    ENTITY_MANAGER_FACTORY.close();
                }
            }
        }