package com.java.ecom.service;

import com.java.ecom.entity.Product;

import java.util.List;

public interface IProductService {
    Product createProduct(Product product);

    Product getProductById(String userId);

    List<Product> getProducts();

    Product updateProduct(Product product);

    void deleteProduct(String userId);
}
