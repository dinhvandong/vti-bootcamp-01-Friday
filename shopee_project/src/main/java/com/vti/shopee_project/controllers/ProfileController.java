package com.vti.shopee_project.controllers;

import com.vti.shopee_project.entities.User;
import com.vti.shopee_project.repositories.UserRepository;
import com.vti.shopee_project.response.ResponseModel;
import com.vti.shopee_project.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/me")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }
        String token = authHeader.substring(7); // Remove "Bearer "
        String email = jwtUtil.extractEmail(token);
        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Not found"));

        if(jwtUtil.isTokenValid(token, email))
        {
            return ResponseEntity.ok(user);
        }else {

            return  ResponseEntity.ok(new ResponseModel("Token is invalid",201, null));

        }
    }
}
