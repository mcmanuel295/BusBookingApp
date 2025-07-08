package com.example.BusBookingApp.utils;

import com.example.BusBookingApp.dto.BookingDto;
import com.example.BusBookingApp.dto.UserDto;
import com.example.BusBookingApp.model.Booking;
import com.example.BusBookingApp.model.User;

public class UtilsService {

    //    USER-DTO TO USER
    public static User toUser(UserDto dto){
        var user =new User();

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setWalletAmount(dto.getAmount());
        return user;
    }

    //    USER TO USER-DTO
    public static UserDto toUserDto(User user){
        return new UserDto(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getWalletAmount()
        );
    }


    //    BOOKING-DTO TO BOOKING
    public static Booking toBooking(BookingDto dto){
        var booking =new Booking();

        booking.setBookingId(dto.getBookingId());
        booking.setOrigin(dto.getOrigin());
        booking.setDestination(dto.getDestination());
        booking.setBookingId(dto.getBookingId());
        booking.setOrigin(dto.getOrigin());
        booking.setDestination(dto.getDestination());
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
