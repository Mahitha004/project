package com.web.employee.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.employee.model.User; // Ensure this import is correct
import com.web.employee.service.UserService; // Ensure this import is correct

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create_user")
    public ResponseEntity<String> createUser(@Validated @RequestBody User user) {
        try {
            userService.createUser(user);
            return ResponseEntity.ok("User created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/get_users")
    public ResponseEntity<List<User>> getUsers(
            @RequestParam Optional<String> mobNum,
            @RequestParam Optional<UUID> userId,
            @RequestParam Optional<UUID> managerId) {
        List<User> users = userService.getUsers(mobNum, userId, managerId);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/delete_user")
    public ResponseEntity<String> deleteUser(@RequestBody UUID userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/update_user")
    public ResponseEntity<String> updateUser(@RequestBody UUID userId, @Validated @RequestBody User user) {
        try {
            userService.updateUser(userId, user);
            return ResponseEntity.ok("User updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
