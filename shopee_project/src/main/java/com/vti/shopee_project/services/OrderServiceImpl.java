package com.vti.shopee_project.services;

import com.vti.shopee_project.entities.Order;
import com.vti.shopee_project.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepo;

    public Order placeOrder(Order order) {
        order.setStatus("PENDING");
        order.setCreatedAt(new Date());
        return orderRepo.save(order);
    }

    public List<Order> getOrdersByUser(String userId) {
        return orderRepo.findByUserId(userId);
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Order updateOrderStatus(String id, String status) {
        Order order = orderRepo.findById(id).orElseThrow();
        order.setStatus(status);
        return orderRepo.save(order);
    }
}

