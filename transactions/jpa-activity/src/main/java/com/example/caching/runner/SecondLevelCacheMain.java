package com.example.caching.runner;

import com.example.caching.entity.SecondLevelCacheProduct;
import com.example.caching.manager.SecondLevelCacheProductManager;
import com.example.util.JPAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecondLevelCacheMain {

    private static final Logger logger = LoggerFactory.getLogger(SecondLevelCacheMain.class);

    public static void main(String[] args) {
        SecondLevelCacheProductManager manager = new SecondLevelCacheProductManager();

        // Create and add a product
        SecondLevelCacheProduct product = new SecondLevelCacheProduct(null, "Laptop", "Electronics", 1200.00, 10.0, 500, 4.5);

        // Print cache contents before operations
        manager.printCacheContents();

        manager.addProduct(product);

        // Print cache contents before operations
        manager.printCacheContents();

        // Demonstrate Second-Level Cache
        manager.demonstrateSecondLevelCache();

        // Print cache contents after operations
        manager.printCacheContents();

        // Close the entity manager factory
        JPAUtil.close();
    }
}
