package com.example.BusBookingApp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDto{
    Long bookingId;
    private String origin;
    String destination;
    LocalDateTime departureDateTime;

}
