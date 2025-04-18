package com.vti.shopee_project.services;

import com.vti.shopee_project.entities.User;
import com.vti.shopee_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public User register(User user) {
        // Check existing, hash password, validate
        return userRepo.save(user);
    }

    @Override
    public User login(String email, String password) {
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Check hashed password match
            return user;
        }
        throw new RuntimeException("Invalid credentials");
    }

    public Optional<User> findById(String id) {
        return userRepo.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
