package com.example.BusBookingApp.service.Interface;

import com.example.BusBookingApp.UserRequest;
import com.example.BusBookingApp.dto.UserDto;
import com.example.BusBookingApp.model.LoginRequest;
import com.example.BusBookingApp.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    UserDto createUser(UserRequest userRequest);

    UserDto getUser(Long id);

    List<UserDto> getAllUsers();

    void deleteUser(long id);

    UserDto updateUser(long userId,User user);

    String login(LoginRequest user);
<<<<<<< HEAD
=======

//    account payment
    void payment(Long userId, BigDecimal amount);
>>>>>>> f0ac1cd9a228c3c8b560a8d4a325143ab3c68480

    UserDto withdraw(long userId,BigDecimal amount);

    UserDto deposit(long userId,BigDecimal amount);
}
