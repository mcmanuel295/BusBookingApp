package com.example.BusBookingApp.controller;

import com.example.BusBookingApp.model.Bus;
import com.example.BusBookingApp.model.Route;
import com.example.BusBookingApp.service.Interface.BusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/buses")
public class BusController {

    private final BusService service;

    public BusController(BusService service) {
        this.service = service;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Bus> createBus(int capacity){
        return new ResponseEntity<>(service.createBus(capacity), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBus(int budId){
        return ResponseEntity.ok().body(service.getBus(budId));
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Bus> getAllBuses(){
        return service.getAllBuses();
    }

    @PutMapping("/{id}/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Bus> registerBus(Bus bus){
        return ResponseEntity.ok().body(service.registerBus(bus.getBusNumber(),bus.getCapacity(),bus.getDriver()));
    }

    @PutMapping("/{id}/route")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Bus> assignRoute(int busId, Route startRoute,Route endRoute){
        return ResponseEntity.ok().body( service.assignRoute(busId,startRoute,endRoute));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteBus(int busId){
        service.deleteBus(busId);
        return ResponseEntity.ok().body("Bus id "+busId+" has been deleted");
    }
}
