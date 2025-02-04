package com.example.BusBookingApp.service.Imp;

import com.example.BusBookingApp.exception.BusNotFoundException;
import com.example.BusBookingApp.model.Bus;
import com.example.BusBookingApp.model.Driver;
import com.example.BusBookingApp.model.Route;
import com.example.BusBookingApp.repository.BusRepository;
import com.example.BusBookingApp.service.Interface.BusService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public Bus registerBus(int busId, int capacity, MultipartFile file) {
        Bus newBus =repo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus with busId "+busId+" Does Not Exist!!"));
        return repo.save(newBus);
    }


    @Override
    public Bus assignRoute(int busId, Route startRoute, Route endRoute) {
        Bus bus = repo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus with busId "+busId+" Not Found!!!"));
        bus.setStartRoute(startRoute);
        bus.setStartRoute(endRoute);

        return repo.save(bus);
    }


    @Override
    public Bus getBus(int busId) {
        return  repo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus with busId" +busId+" Not Found"));
    }


    @Override
    public List<Bus> getAllBuses() {
        return repo.findAll().stream().toList();
    }


    @Override
    public List<Bus> getAllBusesWithPagination(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Bus> page = repo.findAll(pageable);
        return page.getContent();
    }

    @Override
    public int[] getAllBusesNumber() {
        return getAllBuses()
                .stream()
                .map(Bus::getBusNumber)
                .toList().stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    @Override
    public void deleteBus(int busId) {
        getBus(busId);
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

        if (Collections.disjoint(startRouteBus,endRouteBus)) {
            throw new BusNotFoundException("Bus With start route "+startRoute +"and end route"+endRoute+" Unavailable");
        }
        return startRouteBus.stream().filter(endRouteBus::contains).toList();
    }

    @Override
    public List<Bus> findByRouteWithPagination(Route startRout, Route endRoute) {
        return List.of();
    }
}