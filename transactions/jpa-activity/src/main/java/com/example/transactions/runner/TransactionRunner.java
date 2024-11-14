package com.example.transactions.runner;

import com.example.transactions.entity.ProductSales;
import com.example.transactions.manager.ProductManager;
import com.example.util.JPAUtil;

public class TransactionRunner {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();

        // Create and add products with various sales figures
        ProductSales product1 = new ProductSales(null, "Laptop", "Electronics", 1200.00, 10.0, 500, 4.5);
        ProductSales product2 = new ProductSales(null, "Smartphone", "Electronics", 800.00, 15.0, 300, 4.7);
        ProductSales product3 = new ProductSales(null, "Tablet", "Electronics", 600.00, 5.0, 200, 4.6);
        ProductSales product4 = new ProductSales(null, "Headphones", "Electronics", 200.00, 20.0, 1000, 4.2);
        ProductSales product5 = new ProductSales(null, "Smartwatch", "Electronics", 300.00, 5.0, 250, 4.3);

        // Add products to the database
        manager.addProduct(product1);
        manager.addProduct(product2);
        manager.addProduct(product3);
        manager.addProduct(product4);
        manager.addProduct(product5);

        // Adjust prices and discounts for products with sales greater than 200
        manager.updatePricesAndDiscounts(250, 10, 5);

        // Print updated product details
        System.out.println("Updated product details:");
        System.out.println(manager.findProductById(product1.getId()));
        System.out.println(manager.findProductById(product2.getId()));
        System.out.println(manager.findProductById(product3.getId()));
        System.out.println(manager.findProductById(product4.getId()));
        System.out.println(manager.findProductById(product5.getId()));

        // Close the entity manager factory
        JPAUtil.close();
    }
}
