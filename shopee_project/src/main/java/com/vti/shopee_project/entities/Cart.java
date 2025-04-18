package com.vti.shopee_project.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "carts")
public class Cart {
    @Id
    private String id;
    private String userId;
    private List<CartItem> items;
    private Date updatedAt;

    public Cart(String userId) {
        this.userId = userId;
        this.items = new ArrayList<>();
        this.updatedAt = new Date();
    }

    public static class CartItem {
        private String productId;
        private String title;
        private int quantity;
        private double price;

        // Getters, Setters, Constructors
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
// Getters, Setters, Constructors
}
