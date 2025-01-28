package com.example.BusBookingApp.service.Interface;

import com.example.BusBookingApp.dto.BookingDto;
import com.example.BusBookingApp.model.Booking;
import com.example.BusBookingApp.model.Bus;
import com.example.BusBookingApp.model.Route;

import java.util.List;

public interface BookingService {

    BookingDto addBooking(Booking booking);

    BookingDto addBookingByRoute(Booking booking, Bus bus);

    BookingDto getBookings(long userId) ;

    void deleteBooking(Long id) ;

    List<BookingDto> getAllBookings();

    BookingDto update(Long dtoId, Booking booking);


}
