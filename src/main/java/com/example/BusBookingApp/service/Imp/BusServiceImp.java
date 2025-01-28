package com.example.BusBookingApp.service.Imp;

import com.example.BusBookingApp.exception.OurException;
import com.example.BusBookingApp.model.Bus;
import com.example.BusBookingApp.model.Driver;
import com.example.BusBookingApp.model.Route;
import com.example.BusBookingApp.repository.BusRepository;
import com.example.BusBookingApp.service.Interface.BusService;
import org.hibernate.mapping.Collection;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
    public int[] getAllBusesByNumber() {
        return getAllBuses()
                .stream()
                .map(Bus::getBusNumber)
                .toList().stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    @Override
    public void deleteBus(int busId) {
        repo.deleteById(busId);
    }

    @Override
    public void bookASeat(int busId,int seatNumber) {
        Bus bus = getBus(busId);
        bus.bookSeat(seatNumber);
    }



    @Override
    public List<Bus> findByRoute(Route startRoute, Route endRoute) {
        List<Bus> startRouteBus = repo.findAllByStartRoute(startRoute);
        List<Bus> endRouteBus =repo.findAllByEndRoute(endRoute);

        try {
            if (Collections.disjoint(startRouteBus,endRouteBus)) {
                throw new OurException("Bus With Routes Unavailable");
            }
            return startRouteBus.stream().filter(endRouteBus::contains).toList();

        } catch (OurException e) {
            throw new RuntimeException(e);
        }
    }

}