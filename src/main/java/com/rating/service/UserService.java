package com.rating.service;

import com.rating.dtos.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<?> addUser(UserResponse userData);

    ResponseEntity<?> getUser(String userId);

    ResponseEntity<?> getAllUsers();
}
