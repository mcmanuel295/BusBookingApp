package com.example.BusBookingApp.service.Imp;

import com.example.BusBookingApp.dto.BookingDto;
import com.example.BusBookingApp.exception.BookingException;
import com.example.BusBookingApp.exception.BookingNotFoundException;
import com.example.BusBookingApp.model.Booking;
import com.example.BusBookingApp.model.Bus;
import com.example.BusBookingApp.model.User;
import com.example.BusBookingApp.repository.BookingRepository;
import com.example.BusBookingApp.service.Interface.BookingService;
import com.example.BusBookingApp.service.Interface.BusService;
import com.example.BusBookingApp.utils.UtilsService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@Transactional(rollbackOn = Exception.class)
public class BookingServiceImp implements BookingService {
    private final BookingRepository bookingRepo;
    private final BusServiceImp busService;
    private final UserServiceImp userService;

    public BookingServiceImp(BookingRepository repo, BusServiceImp service, UserServiceImp userService) {
        this.bookingRepo = repo;
        this.busService = service;
        this.userService = userService;
    }

    @Override
    public BookingDto addBooking(Booking booking){
        if (!booking.isHasPaid()) {
            throw  new BookingException("Booking Price Not Paid");
        }
        User user = booking.getUser();
        userService.payment(user.getId(), booking.getCost());
        booking.setHasPaid(true);

        Bus bus = busService.getBus(booking.getBusNo() );

        if(
                Arrays.binarySearch( BusService.availableSeats(bus),booking.getBusNo()) != -1 ||
                        Arrays.binarySearch(busService.getAllBusesNumber(),booking.getBusNo())!=-1
        ){

            busService.bookASeat(booking.getBusNo(),booking.getSeatNo());
            var savedBooking = bookingRepo.save(booking);
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
        Booking savedBooking =bookingRepo.findById(bookingId).orElseThrow(()-> new BookingNotFoundException("Booking Not Found!!!!"));
        return UtilsService.toBookingDto(savedBooking);
    }



    @Override
    public void deleteBooking(Long bookingId) {
        getBookings(bookingId);
        bookingRepo.deleteById(bookingId);
    }



    @Override
    public List<BookingDto> getAllBookings() {
        return bookingRepo.findAll().stream()
                .map(UtilsService::toBookingDto)
                .toList();
    }



    @Override
    public BookingDto update(Long bookingId, Booking booking) {
        getBookings(bookingId);
        Booking savedUser =bookingRepo.save(booking);
        return UtilsService.toBookingDto(savedUser);
    }


}
