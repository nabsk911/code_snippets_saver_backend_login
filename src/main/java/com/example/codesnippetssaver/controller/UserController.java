package com.example.codesnippetssaver.controller;

import com.example.codesnippetssaver.model.User;
import com.example.codesnippetssaver.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<User> authenticateUser(@RequestParam String email, @RequestParam String password) {
        return ResponseEntity.ok(userService.authenticateUser(email, password));
    }

    @PostMapping("/add")
    public ResponseEntity<User> registerNewUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addNewUser(user));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUserByEmail(@RequestParam String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }
}
