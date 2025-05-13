package com.vti.shopee_project.controllers;

import com.vti.shopee_project.entities.Order;
import com.vti.shopee_project.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/filter")
    public List<Order> getOrdersBetweenDates(
            @RequestParam Date start,
            @RequestParam Date end) {
        return orderRepository.findByCreatedAtBetween(start, end);
    }
}