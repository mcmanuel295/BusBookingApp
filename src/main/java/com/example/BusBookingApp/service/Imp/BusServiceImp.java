package com.example.BusBookingApp.service.Imp;

import com.example.BusBookingApp.model.Bus;
import com.example.BusBookingApp.model.Driver;
import com.example.BusBookingApp.model.Route;
import com.example.BusBookingApp.repository.BusRepository;
import com.example.BusBookingApp.service.Interface.BusService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImp implements BusService {

    private final BusRepository repo;

    public BusServiceImp(BusRepository repo) {
        this.repo = repo;
    }

    @Override
    public Bus createBus(int capacity) {
        return repo.save(new Bus(capacity,null));
    }

    @Override
    public Bus registerBus(int busId, int capacity, Driver driver) {
        Bus newBus =repo.findById(busId).orElseThrow(()-> new RuntimeException("Bus Does Not Exist!!"));
        newBus.setDriver(driver);
        return repo.save(newBus);
    }

    @Override
    public Bus assignRoute(int busId, Route startRoute, Route endRoute) {
        Bus bus = repo.findById(busId).orElseThrow(()-> new UsernameNotFoundException("Bus Not Found!!!"));
        bus.setStartRoute(startRoute);
        bus.setStartRoute(endRoute);

        return repo.save(bus);
    }

    @Override
    public Bus getBus(int budId) {
        return  repo.findById(budId).orElseThrow(()-> new RuntimeException("Bus Not Found"));
    }

    @Override
    public List<Bus> getAllBuses() {
        return repo.findAll().stream().toList();
    }

    @Override
    public void deleteBus(int busId) {
        repo.deleteById(busId);
    }

    @Override
    public String bookASeat(int busId,int seatNumber) {
        Bus bus = getBus(busId);
        bus.bookSeat(seatNumber);
        return "You have booked seat number "+seatNumber;
    }


}
