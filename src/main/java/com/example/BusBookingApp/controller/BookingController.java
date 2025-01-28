package com.example.BusBookingApp.controller;

import com.example.BusBookingApp.dto.BookingDto;
import com.example.BusBookingApp.model.Booking;
import com.example.BusBookingApp.service.Imp.BookingServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    BookingServiceImp service;

    public BookingController(BookingServiceImp service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<BookingDto> create(Booking dto){
        return ResponseEntity.ok().body(service.addBooking(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookings(Long userId){
        return ResponseEntity.ok().body(service.getBookings(userId));

    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<BookingDto> getAllBookings(){
       return service.getAllBookings();
    }


    @PutMapping("/booking/{id}/update")
    public ResponseEntity<BookingDto> editBooking(@RequestParam Long dtoId,@RequestBody Booking booking){
        return ResponseEntity.ok().body(service.update(dtoId,booking));
    }

    @DeleteMapping("/booking/{id}/delete")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id){
        service.deleteBooking(id);
        return ResponseEntity.ok().body("deleted");
    }


}
