package com.example.BusBookingApp.dto;

import com.example.BusBookingApp.model.Role;
import lombok.Data;

@Data
public class UserDto{
        private String firstName;
        private String lastName;
        private String email;
        private Role role;

}
