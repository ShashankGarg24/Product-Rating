package com.rating.controllers;

import com.rating.dtos.UserResponse;
import com.rating.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody UserResponse userData){
        try{
            return userService.addUser(userData);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to add user!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") String userId){
        try{
            return userService.getUser(userId);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to fetch user for id " + userId + "!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers(){
        try{
            return userService.getAllUsers();
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to fetch users!", HttpStatus.BAD_REQUEST);
        }
    }

}
