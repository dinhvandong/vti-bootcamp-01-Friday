package com.vti.shopee_project.services;

import com.vti.shopee_project.entities.Cart;

public interface CartService {
    Cart getCartByUser(String userId);
    Cart addToCart(String userId, Cart.CartItem item);
    void removeFromCart(String userId, String productId);
    void clearCart(String userId);
}
