package com.example.s3amazon.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.s3amazon.model.dto.UserRequest;
import com.example.s3amazon.model.dto.UserResponse;
import com.example.s3amazon.model.entity.User;
import com.example.s3amazon.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<UserResponse> saveUser(@ModelAttribute UserRequest userRequest) {
        UserResponse profileImageUrl = userService.saveUser(userRequest);
        return ResponseEntity.ok(profileImageUrl);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> user0 = userService.findAll();
        return ResponseEntity.ok(user0);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id) {
        Optional<User> user0 = userService.findById(id);

        if (user0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity.ok(user0.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id) {
        Optional<User> user0 = userService.findById(id);

        if (user0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        userService.delete(id);
        return ResponseEntity.ok(user0);
    }
}
