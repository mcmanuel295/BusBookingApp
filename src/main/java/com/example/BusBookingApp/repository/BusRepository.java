package com.example.BusBookingApp.repository;

import com.example.BusBookingApp.model.Bus;
import com.example.BusBookingApp.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus,Integer> {

    List<Bus> findAllByStartRoute(Route route);
    List<Bus> findAllByEndRoute(Route route);

}
