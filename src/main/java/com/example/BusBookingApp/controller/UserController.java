package com.example.BusBookingApp.controller;

import com.example.BusBookingApp.dto.UserDto;
import com.example.BusBookingApp.model.LoginRequest;
import com.example.BusBookingApp.model.User;
import com.example.BusBookingApp.service.Imp.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.BusBookingApp.model.Role.ADMIN;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImp service;

    public UserController(UserServiceImp service) {
        this.service = service;
        addFirstUsr();
    }
    

    private void addFirstUsr() {
        User firstUser= new User();
        firstUser.setFirstName("Emmanuel");
        firstUser.setLastName("Ogbu");
        firstUser.setPassword("Oea75357@");
        firstUser.setEmail("mcmanuel295@gmail.com");
        firstUser.setRole(ADMIN);

        addUser(firstUser);

    }

    @PostMapping("/")
    public ResponseEntity<UserDto> addUser(@RequestBody User user) {
        System.out.println(user.getEmail()+" "+user.getPassword());
        return new ResponseEntity<>(service.addUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        service.login(loginRequest);
        return ResponseEntity.ok("Logged in");
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
