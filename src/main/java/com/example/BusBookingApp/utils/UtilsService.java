package com.example.BusBookingApp.utils;

import com.example.BusBookingApp.dto.BookingDto;
import com.example.BusBookingApp.dto.UserDto;
import com.example.BusBookingApp.model.Booking;
import com.example.BusBookingApp.model.User;

public class UtilsService {

    //    USER-DTO TO USER
    public static User toUser(UserDto dto){
        var user =new User();

        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setRole(dto.role());
        return user;
    }

    //    USER TO USER-DTO
    public static UserDto toUserDto(User user){
        return new UserDto(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }


    //    BOOKING-DTO TO BOOKING
    public static Booking toBooking(BookingDto dto){
        var booking =new Booking();

        booking.setBookingId(dto.bookingId());
        booking.setOrigin(dto.origin());
        booking.setDestination(dto.destination());
        return booking;
    }

    //    BOOKING TO BOOKING-DTO
    public static BookingDto toBookingDto(Booking booking){
        return new BookingDto(
                booking.getBookingId(),
                booking.getOrigin(),
                booking.getDestination(),
                booking.getDepartureDate()
        );
    }
}
