package com.example.BusBookingApp.repository;

import com.example.BusBookingApp.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus,Integer> {


}
