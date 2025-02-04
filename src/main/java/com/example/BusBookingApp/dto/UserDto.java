package com.example.BusBookingApp.dto;

import com.example.BusBookingApp.model.Role;

public record UserDto(
        String firstName,
        String lastName,
        String email,
        Role role
) {
}
