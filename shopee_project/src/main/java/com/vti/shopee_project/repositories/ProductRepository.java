package com.vti.shopee_project.repositories;



import com.vti.shopee_project.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findBySellerId(String sellerId);
    List<Product> findByCategory(String category);
    List<Product> findByTitleContainingIgnoreCase(String keyword);
}

