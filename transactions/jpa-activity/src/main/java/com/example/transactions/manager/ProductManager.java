package com.example.transactions.manager;

import com.example.transactions.entity.ProductSales;
import com.example.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ProductManager {

    public void updatePricesAndDiscounts(double salesThreshold, double priceIncreasePercentage, double discountDecreasePercentage) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // First update: Increase prices for products with high sales
            int priceUpdateCount = entityManager.createQuery("UPDATE ProductSales p SET p.price = p.price * :increasePercentage WHERE p.sales > :salesThreshold")
                                                .setParameter("increasePercentage", 1 + (priceIncreasePercentage / 100))
                                                .setParameter("salesThreshold", salesThreshold)
                                                .executeUpdate();

            // Find the average price of products
            Double avgPrice = (Double) entityManager.createQuery("SELECT AVG(p.price) FROM ProductSales p").getSingleResult();

            // Second update: Reduce discounts for the same products if the price is higher than the average price
            int discountUpdateCount = entityManager.createQuery("UPDATE ProductSales p SET p.discount = p.discount * :decreasePercentage WHERE p.sales > :salesThreshold AND p.price > :avgPrice")
                                                   .setParameter("decreasePercentage", 1 - (discountDecreasePercentage / 100))
                                                   .setParameter("salesThreshold", salesThreshold)
                                                   .setParameter("avgPrice", avgPrice)
                                                   .executeUpdate();

            transaction.commit();
            System.out.println("Updated " + priceUpdateCount + " product prices and " + discountUpdateCount + " product discounts.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void addProduct(ProductSales product) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public ProductSales findProductById(Long productId) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        ProductSales product = entityManager.find(ProductSales.class, productId);
        entityManager.close();
        return product;
    }

    public List<ProductSales> getAllProducts() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<ProductSales> products = entityManager.createQuery("SELECT p FROM Product p", ProductSales.class).getResultList();
        entityManager.close();
        return products;
    }
}
