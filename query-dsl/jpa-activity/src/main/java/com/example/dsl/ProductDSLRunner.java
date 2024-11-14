package com.example.dsl;

import com.example.entity.Product;
import com.example.entity.QProduct;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProductDSLRunner {
    public static void main(String[] args) {
        EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("activity-app");
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        QProduct qProduct = QProduct.product;

//        Price is greater than 100

//        BooleanExpression predicate = qProduct.price.gt(100.00);
//        List<Product> products = queryFactory.selectFrom(qProduct) .where(predicate).fetch();
//        products.forEach(product -> System.out.println("Found product: " + product.getName() + ", Price: " + product.getPrice()));

//        Rating is between 3.8 and 4.5
//        BooleanExpression ratingBetween = qProduct.rating.between(3.8, 4.5);
//        List<Product> products = queryFactory.selectFrom(qProduct).where(ratingBetween).fetch();
//        products.forEach(product -> System.out.println("Found product: " + product.getName() + ", Rating: " + product.getRating()));

//        Discounts are in 0.1, 0.15, 0.25
//        BooleanExpression discountIn = qProduct.discount.in(0.1, 0.15, 0.25);
//        List<Product> products = queryFactory.selectFrom(qProduct).where(discountIn).fetch();
//        products.forEach(product -> System.out.println("Found product: " + product.getName() + ", Discount: " + product.getDiscount()));

//        Average of all ratings
//        NumberExpression<Double> avgRatingExpression = qProduct.rating.avg();
//        Double averageRating = queryFactory.select(avgRatingExpression).from(qProduct) .fetchOne();
//        System.out.println("Average rating of all products: " + averageRating);

//        Group products by their ratings and show their average price
        // select avg(price), rating from products group by (rating)
//        List<Tuple> results = queryFactory.select(qProduct.rating, qProduct.price.avg()).from(qProduct).groupBy(qProduct.rating).fetch();
//        for (Tuple tuple : results) {
//            Double rating = tuple.get(qProduct.rating);
//            Double avgPrice = tuple.get(qProduct.price.avg());
//            System.out.println("Rating: " + rating + ", Average Price: " + avgPrice);
//        }

//        Sort the products based on their price
//        List<Product> products = queryFactory.selectFrom(qProduct) .orderBy(qProduct.price.asc()) .fetch();
//        products.forEach(product -> System.out.println("Product: " + product.getName() + ", Price: " + product.getPrice()));

//        Products with Price Greater Than the Average Price
//        NumberPath<Double> avgPricePath = Expressions.numberPath(Double.class, "avgPrice");
//        Double averagePrice = queryFactory.select(qProduct.price.avg()) .from(qProduct) .fetchOne();
//        List<Product> products = queryFactory.selectFrom(qProduct) .where(qProduct.price.gt(averagePrice)) .fetch();
//        products.forEach(product -> System.out.println("Product: " + product.getName() + ", Price: " + product.getPrice()));


        entityManager.close();
        ENTITY_MANAGER_FACTORY.close();
    }
}