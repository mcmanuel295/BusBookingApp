package com.example.BusBookingApp.dto;

import java.time.LocalDateTime;

public record BookingDto(
        Long bookingId,
        String origin,
        String destination,
        LocalDateTime departureDateTime
)
{}
