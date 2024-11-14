package com.example.dsl;

import com.example.dao.ProductDAO;
import com.example.entity.Product;
import com.example.impl.ProductDAOImpl;

public class ProductInserter {
    public static void main(String[] args) {

                ProductDAO productDAO = new ProductDAOImpl();

                // Create and insert each product individually
                Product product1 = new Product(null, "Laptop", "Electronics", 999.99, 0.1, 150, 4.5);
                productDAO.insert(product1);

                Product product2 = new Product(null, "Smartphone", "Electronics", 499.99, 0.2, 300, 4.7);
                productDAO.insert(product2);

                Product product3 = new Product(null, "TV", "Electronics", 799.99, 0.15, 120, 4.3);
                productDAO.insert(product3);

                Product product4 = new Product(null, "Headphones", "Electronics", 199.99, 0.05, 220, 4.1);
                productDAO.insert(product4);

                Product product5 = new Product(null, "Refrigerator", "Home Appliances", 1499.99, 0.25, 60, 4.8);
                productDAO.insert(product5);

                Product product6 = new Product(null, "Washing Machine", "Home Appliances", 699.99, 0.1, 80, 4.6);
                productDAO.insert(product6);

                Product product7 = new Product(null, "Blender", "Home Appliances", 89.99, 0.05, 90, 4.2);
                productDAO.insert(product7);

                Product product8 = new Product(null, "Microwave", "Home Appliances", 129.99, 0.2, 110, 4.4);
                productDAO.insert(product8);

                Product product9 = new Product(null, "Toaster", "Home Appliances", 49.99, 0.15, 100, 4.0);
                productDAO.insert(product9);

                Product product10 = new Product(null, "Coffee Maker", "Home Appliances", 79.99, 0.1, 130, 4.5);
                productDAO.insert(product10);

                Product product11 = new Product(null, "Gaming Console", "Electronics", 399.99, 0.3, 200, 4.9);
                productDAO.insert(product11);

                Product product12 = new Product(null, "Smartwatch", "Electronics", 249.99, 0.1, 180, 4.3);
                productDAO.insert(product12);

                Product product13 = new Product(null, "Camera", "Electronics", 599.99, 0.2, 140, 4.4);
                productDAO.insert(product13);

                Product product14 = new Product(null, "Printer", "Office Supplies", 149.99, 0.05, 70, 3.8);
                productDAO.insert(product14);

                Product product15 = new Product(null, "Desk", "Office Supplies", 199.99, 0.15, 50, 4.2);
                productDAO.insert(product15);

                Product product16 = new Product(null, "Chair", "Office Supplies", 99.99, 0.2, 60, 4.1);
                productDAO.insert(product16);

                Product product17 = new Product(null, "Lamp", "Office Supplies", 39.99, 0.1, 90, 4.0);
                productDAO.insert(product17);

                Product product18 = new Product(null, "Notebook", "Stationery", 19.99, 0.05, 300, 3.9);
                productDAO.insert(product18);

                Product product19 = new Product(null, "Pen", "Stationery", 2.99, 0.1, 500, 4.5);
                productDAO.insert(product19);

                Product product20 = new Product(null, "Backpack", "Accessories", 49.99, 0.2, 150, 4.3);
                productDAO.insert(product20);

                System.out.println("Inserted 20 products.");
            }
        }


