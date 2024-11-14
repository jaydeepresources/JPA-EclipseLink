package com.example.dao;

import com.example.entity.Product;

import java.util.List;

public interface ProductDAO {
    Product insert(Product product);
    Product findById(Long id);
    List<Product> findAll();
    Product update(Product product);
    void deleteById(Long id);
}
