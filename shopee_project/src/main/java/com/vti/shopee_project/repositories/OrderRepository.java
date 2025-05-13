package com.vti.shopee_project.repositories;


import com.vti.shopee_project.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUserId(String userId);
    List<Order> findByStatus(String status);
    List<Order> findByCreatedAtBetween(Date startDate, Date endDate);

}

