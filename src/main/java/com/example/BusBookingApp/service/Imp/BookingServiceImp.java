package com.example.BusBookingApp.service.Imp;

import com.example.BusBookingApp.dto.BookingDto;
import com.example.BusBookingApp.exception.BookingNotFoundException;
import com.example.BusBookingApp.model.Booking;
import com.example.BusBookingApp.model.Bus;
import com.example.BusBookingApp.repository.BookingRepository;
import com.example.BusBookingApp.service.Interface.BookingService;
import com.example.BusBookingApp.service.Interface.BusService;
import com.example.BusBookingApp.utils.UtilsService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class BookingServiceImp implements BookingService {
    private final BookingRepository repo;
    private final BusServiceImp service;

    public BookingServiceImp(BookingRepository repo, BusServiceImp service) {
        this.repo = repo;
        this.service = service;
    }

    @Override
    public BookingDto addBooking(Booking booking){
        Bus bus = service.getBus(booking.getBusNo() );

        if(
                Arrays.binarySearch( BusService.availableSeats(bus),booking.getBusNo()) != -1 ||
                        Arrays.binarySearch(service.getAllBusesByNumber(),booking.getBusNo())!=-1
        ){

            service.bookASeat(booking.getBusNo(),booking.getSeatNo());
            var savedBooking = repo.save(booking);
            return UtilsService.toBookingDto(savedBooking);
        }
        throw new RuntimeException("Enter Correct Credentials");
    }



    @Override
    public BookingDto addBookingByRoute(Booking booking,Bus bus) {
        booking.setBusNo(bus.getBusNumber());
        return addBooking(booking);
    }


    @Override
    public BookingDto getBookings(long bookingId) {
        Booking savedBooking =repo.findById(bookingId).orElseThrow(()-> new BookingNotFoundException("Booking Not Found!!!!"));
        return UtilsService.toBookingDto(savedBooking);
    }



    @Override
    public void deleteBooking(Long bookingId) {
        getBookings(bookingId);
        repo.deleteById(bookingId);
    }



    @Override
    public List<BookingDto> getAllBookings() {
        return repo.findAll().stream()
                .map(UtilsService::toBookingDto)
                .toList();
    }



    @Override
    public BookingDto update(Long bookingId, Booking booking) {
        getBookings(bookingId);
        Booking savedUser =repo.save(booking);
        return UtilsService.toBookingDto(savedUser);
    }


}
