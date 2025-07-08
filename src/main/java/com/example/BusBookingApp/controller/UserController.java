package com.example.BusBookingApp.controller;

import com.example.BusBookingApp.UserRequest;
import com.example.BusBookingApp.dto.UserDto;
import com.example.BusBookingApp.model.LoginRequest;
import com.example.BusBookingApp.model.User;
import com.example.BusBookingApp.service.Imp.UserServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.example.BusBookingApp.model.Role.ADMIN;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImp service;

//    public UserController(UserServiceImp service) {
//        this.service = service;
//        addFirstUser();
//    }
//

//    private void addFirstUser() {
//        UserRequest firstUser= new UserRequest();
//        firstUser.setFirstName("Emmanuel");
//        firstUser.setLastName("Ogbu");
//        firstUser.setPassword("Oea75357@");
//        firstUser.setEmail("mcmanuel295@gmail.com");
//        firstUser.setRole(ADMIN);
//
//        addUser(firstUser);
//
//    }

    @PostMapping("/")
    public ResponseEntity<UserDto> addUser(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(service.createUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
<<<<<<< HEAD
        if (service.login(loginRequest).equals("failed")) {
            return ResponseEntity.ok("Error logging in");
        }
=======
        service.login(loginRequest);
>>>>>>> f0ac1cd9a228c3c8b560a8d4a325143ab3c68480
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
    public ResponseEntity<UserDto> updateUser(@PathVariable @Valid long userId,@RequestBody @Valid User user) {
        return new ResponseEntity<>(service.updateUser(userId,user),HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<UserDto> userWithdrawal(@PathVariable @Valid long userId,@RequestBody @Valid BigDecimal amount) {
        return new ResponseEntity<>(service.withdraw(userId,amount),HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<UserDto> userDeposit(@PathVariable @Valid long userId,@RequestBody @Valid BigDecimal amount) {
        return new ResponseEntity<>(service.deposit(userId,amount),HttpStatus.ACCEPTED);
    }
}
