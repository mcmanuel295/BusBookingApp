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

//    account payment
    void payment(Long userId, BigDecimal amount);

    BigDecimal withdraw(User user,BigDecimal amount);
}
