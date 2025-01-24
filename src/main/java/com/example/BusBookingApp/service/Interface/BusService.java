package com.example.BusBookingApp.service.Interface;

import com.example.BusBookingApp.model.Bus;
import com.example.BusBookingApp.model.Driver;
import com.example.BusBookingApp.model.Route;

import java.util.ArrayList;
import java.util.List;

public interface BusService {
    Bus createBus(int capacity);

    Bus registerBus(int busId, int capacity, Driver driver);

    Bus assignRoute(int busId, Route startRoute,Route endRoute);

    Bus getBus(int budId);

    List<Bus> getAllBuses();

    void deleteBus(int busId);

    static int[] availableSeats(Bus bus) {
        ArrayList<Integer> store = new ArrayList<>();


        for (int i = 0; i <bus.getCapacity() ; i++) {
            if (bus.isSeatTaken(i)) {
                store.add(i);
            }
        }
        return store.stream().mapToInt(Integer::intValue).toArray();
    }

    String bookASeat(int busId,int seatNumber);

}
