package com.vti.shopee_project.services;

import com.vti.shopee_project.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User register(User user);
    User login(String email, String password);
    Optional<User> findById(String id);
    List<User> getAllUsers();
}

