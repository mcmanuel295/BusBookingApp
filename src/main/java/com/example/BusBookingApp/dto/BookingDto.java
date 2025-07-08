package com.example.BusBookingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

<<<<<<< HEAD
@Data
@AllArgsConstructor
public class BookingDto{
    Long bookingId;
    private String origin;
    String destination;
    LocalDateTime departureDateTime;

}
=======
public record BookingDto(
        Long bookingId,
        String origin,
        String destination,
        LocalDateTime departureDateTime
)
{}
>>>>>>> f0ac1cd9a228c3c8b560a8d4a325143ab3c68480
