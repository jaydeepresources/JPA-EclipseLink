package com.example.impl;

import com.example.dao.ProductDAO;
import com.example.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("activity-app");

    @Override
    public Product insert(Product product) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(product);
            entityTransaction.commit();
        } finally {
            entityManager.close();
        }
        return product;
    }

    @Override
    public Product findById(Long id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Product product = null;
        try {
            product = entityManager.find(Product.class, id);
        } finally {
            entityManager.close();
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        List<Product> products = null;
        try {
            products = entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        } finally {
            entityManager.close();
        }
        return products;
    }

    @Override
    public Product update(Product product) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Product updatedProduct = null;
        try {
            entityTransaction.begin();
            updatedProduct = entityManager.merge(product);
            entityTransaction.commit();
        } finally {
            entityManager.close();
        }
        return updatedProduct;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Product product = entityManager.find(Product.class, id);
            if (product != null) {
                entityManager.remove(product);
            }
            entityTransaction.commit();
        } finally {
            entityManager.close();
        }
    }
}