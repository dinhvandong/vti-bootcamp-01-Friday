package com.vti.shopee_project.repositories;


import com.vti.shopee_project.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    List<Category> findByParentId(String parentId);
}
