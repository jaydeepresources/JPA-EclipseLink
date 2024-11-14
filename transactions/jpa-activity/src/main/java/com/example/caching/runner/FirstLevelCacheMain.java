package com.example.caching.runner;

import com.example.caching.entity.FirstLevelCacheProduct;
import com.example.caching.manager.FirstLevelCacheProductManager;
import com.example.util.JPAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstLevelCacheMain {

    private static final Logger logger = LoggerFactory.getLogger(FirstLevelCacheMain.class);

    public static void main(String[] args) {
        FirstLevelCacheProductManager manager = new FirstLevelCacheProductManager();

        // Create and add a product
        FirstLevelCacheProduct product = new FirstLevelCacheProduct(null, "Laptop", "Electronics", 1200.00, 10.0, 500, 4.5);
        manager.addProduct(product);

        // Demonstrate First-Level Cache
        manager.demonstrateFirstLevelCache();

        // Close the entity manager factory
        JPAUtil.close();
    }
}
