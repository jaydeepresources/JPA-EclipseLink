package com.example.projection;

import com.example.entity.QActivity;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DSLProjectionRunner {
    public static void main(String[] args) {
        EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("activity-app");
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        QActivity qActivity = QActivity.activity;
        List<Tuple> results = queryFactory.select(qActivity.id, qActivity.name).from(qActivity).fetch();

        for (Tuple tuple : results) {
            Long id = tuple.get(qActivity.id);
            String name = tuple.get(qActivity.name);
            System.out.println("Id: " + id + ", Name: " + name);
        }

        entityManager.close();
        ENTITY_MANAGER_FACTORY.close();
    }
}
