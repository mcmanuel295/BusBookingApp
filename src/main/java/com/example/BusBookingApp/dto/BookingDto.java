package com.example.BusBookingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BookingDto{
    Long bookingId;
    private String origin;
    String destination;
    LocalDateTime departureDateTime;

}
