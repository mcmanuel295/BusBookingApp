package com.example.BusBookingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UserDto{
        private String firstName;
        private String lastName;
        private String email;
        private BigDecimal amount;


}
