package com.example.BusBookingApp.service.Interface;

import com.example.BusBookingApp.model.Bus;
import com.example.BusBookingApp.model.Route;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface BusService {
    Bus createBus(int capacity);

    Bus registerBus(int busId, int capacity, MultipartFile file);

    Bus getBus(int budId);

    List<Bus> getAllBuses();

    List<Bus> getAllBusesWithPagination(Integer pageNumber,Integer pageSize);

    Bus assignRoute(int busId, Route startRoute,Route endRoute);

    int[] getAllBusesNumber();

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


    void bookASeat(int busId,int seatNumber);

    List<Bus> findByRoute(Route startRout,Route endRoute);

    List<Bus> findByRouteWithPagination (Route startRout,Route endRoute);


}
