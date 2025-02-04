package com.example.BusBookingApp.service.Interface;

import com.example.BusBookingApp.dto.UserDto;
import com.example.BusBookingApp.model.LoginRequest;
import com.example.BusBookingApp.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    UserDto addUser(User user);

    UserDto getUser(Long id);

    List<UserDto> getAllUsers();

    void deleteUser(long id);

    UserDto updateUser(User user);

    String login(LoginRequest user);

//    account payment
    void payment(Long userId, BigDecimal amount);

}
