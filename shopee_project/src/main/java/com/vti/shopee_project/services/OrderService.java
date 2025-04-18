package com.vti.shopee_project.services;

import com.vti.shopee_project.entities.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(Order order);
    List<Order> getOrdersByUser(String userId);
    List<Order> getAllOrders();
    Order updateOrderStatus(String id, String status);
}

