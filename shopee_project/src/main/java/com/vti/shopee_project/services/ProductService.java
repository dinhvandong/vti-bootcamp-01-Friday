package com.vti.shopee_project.services;

import com.vti.shopee_project.entities.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    List<Product> getProductsBySeller(String sellerId);
    List<Product> searchByKeyword(String keyword);
    Product updateProduct(String id, Product updatedProduct);
    void deleteProduct(String id);
}
