package com.example.BusBookingApp.controller;

import com.example.BusBookingApp.dto.UserDto;
import com.example.BusBookingApp.model.User;
import com.example.BusBookingApp.service.Imp.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImp service;

    public UserController(UserServiceImp service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> addUser(@RequestBody User user) {
        return new ResponseEntity<>(service.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        return new ResponseEntity<>(service.getUser(userId),HttpStatus.FOUND);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> getAllUsers() {
        return service.getAllUsers();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId) {
        service.deleteUser(userId);
        return ResponseEntity.ok().body("user "+userId+" has been deleted");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(User user) {
        return new ResponseEntity<>(service.updateUser(user),HttpStatus.ACCEPTED);
    }
}
