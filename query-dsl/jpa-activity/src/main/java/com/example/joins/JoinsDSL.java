package com.example.joins;

import com.example.entity.QActivity;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class JoinsDSL {
    public static void main(String[] args) {
        EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("activity-app");
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        QUser qUser = QUser.user;
        QUserActivity qUserActivity = QUserActivity.userActivity;

//        List<Tuple> results = queryFactory.select(qUserActivity, qUser).from(qUserActivity).join(qUserActivity.user, qUser).fetch();
//
//        for (Tuple tuple : results) {
//            UserActivity userActivity = tuple.get(qUserActivity);
//            User user = tuple.get(qUser);
//            System.out.println("UserActivity: " + userActivity.getName() + ", User: " + user.getName());
//        }

//        Join based on user's id
        List<UserActivity> activities = queryFactory.selectFrom(qUserActivity).join(qUserActivity.user, qUser).where(qUser.id.eq(5L)).fetch();
        for (UserActivity userActivity : activities) {
            System.out.println("UserActivity: " + userActivity.getName() + ", Description: " + userActivity.getDescription() + ", Duration: " + userActivity.getDuration());
        }

        entityManager.close();
        ENTITY_MANAGER_FACTORY.close();
    }
}
