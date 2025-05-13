package com.vti.shopee_project.services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vti.shopee_project.entities.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductMemoryService {
    private List<Product> allProducts = new ArrayList<>();
    @PostConstruct
    public void loadProductsFromJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new ClassPathResource("products_1000_detailed.json").getInputStream();
            allProducts = mapper.readValue(inputStream, new TypeReference<List<Product>>() {});
            System.out.println("✅ Loaded " + allProducts.size() + " products from JSON.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  List<Product> getAllProducts(){
        return allProducts;
    }

    public List<Product> findRelevantProducts(String query, int limit) {
        String lowerQuery = query.toLowerCase();

        return allProducts.stream()
                .map(p -> {
                    int score = 0;
                    if (Optional.ofNullable(p.getTitle()).orElse("").toLowerCase().contains(lowerQuery)) score += 3;
                    if (Optional.ofNullable(p.getDescription()).orElse("").toLowerCase().contains(lowerQuery)) score += 2;
                    if (Optional.ofNullable(p.getCategory()).orElse("").toLowerCase().contains(lowerQuery)) score += 1;
                    return new AbstractMap.SimpleEntry<>(p, score);
                })
                .filter(entry -> entry.getValue() > 0)
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}

