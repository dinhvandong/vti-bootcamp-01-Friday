package com.vti.shopee_project.services;

import com.vti.shopee_project.entities.Product;
import com.vti.shopee_project.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepo;

    public Product createProduct(Product product) {
        product.setCreatedAt(new Date());
        return productRepo.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public List<Product> getProductsBySeller(String sellerId) {
        return productRepo.findBySellerId(sellerId);
    }

    public List<Product> searchByKeyword(String keyword) {
        return productRepo.findByTitleContainingIgnoreCase(keyword);
    }

    public Product updateProduct(String id, Product updated) {
        Product p = productRepo.findById(id).orElseThrow();
        updated.setId(p.getId());
        return productRepo.save(updated);
    }

    public void deleteProduct(String id) {
        productRepo.deleteById(id);
    }
}

