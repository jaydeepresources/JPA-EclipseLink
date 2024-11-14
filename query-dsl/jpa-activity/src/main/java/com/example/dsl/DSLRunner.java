package com.example.dsl;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DSLRunner {
    public static void main(String[] args) {
        EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("activity-app");
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        entityManager.close();
        ENTITY_MANAGER_FACTORY.close();
    }
}
