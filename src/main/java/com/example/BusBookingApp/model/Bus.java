package com.example.BusBookingApp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;

@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer busNumber;

    @Column(nullable = false)
    private int capacity;

    @OneToOne
    private Driver driver;

    private Route startRoute;

    private Route endRoute ;

    private ArrayList<LocalTime> departureTime = new ArrayList<>();

    private final boolean[] seat = new boolean[capacity];



    public Bus(int capacity, Driver driver) {
        this.capacity = capacity;
        this.driver = driver;

    }

    public Integer getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(Integer busNumber) {
        this.busNumber = busNumber;
    }

    public int getCapacity() {
        return capacity;
    }


    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Route getStartRoute() {
        return startRoute;
    }

    public void setStartRoute(Route startRoute) {
        this.startRoute = startRoute;
    }

    public Route getEndRoute() {
        return this.endRoute;
    }

    public void setEndRoute(Route endRoute) {
        this.endRoute = endRoute;
    }

    public ArrayList<LocalTime> getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(ArrayList<LocalTime> departureTime) {
        this.departureTime = departureTime;
    }

    public boolean isSeatTaken(int seatNo) {
        return seat[seatNo];
    }

    public boolean[] getSeat() {
        return seat;
    }

    public void bookSeat(int seatNumber){
        this.seat[seatNumber]=true;
    }
}
