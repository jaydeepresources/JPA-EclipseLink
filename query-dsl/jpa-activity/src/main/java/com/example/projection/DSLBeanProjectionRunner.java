package com.example.projection;

import com.example.entity.QActivity;
import com.example.dto.ActivityDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DSLBeanProjectionRunner {
    public static void main(String[] args) {
        EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("activity-app");
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        QActivity qActivity = QActivity.activity;

        List<ActivityDTO> activities = queryFactory.select(Projections.bean(ActivityDTO.class, qActivity.id, qActivity.name)).from(qActivity).fetch();

        activities.forEach(activity -> System.out.println("Id: " + activity.getId() + ", Name: " + activity.getName()));

        entityManager.close();
        ENTITY_MANAGER_FACTORY.close();
    }
}