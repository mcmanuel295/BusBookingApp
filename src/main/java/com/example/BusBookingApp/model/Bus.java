package com.example.BusBookingApp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer busNumber;

    @Column(nullable = false)
    private int capacity;

    private Route startRoute;

    private Route endRoute ;

    private ArrayList<LocalTime> departureTime = new ArrayList<>();

    private final boolean[] seat = new boolean[capacity];



    public Bus(int capacity, Driver driver) {
        this.capacity = capacity;
    }


    public boolean isSeatTaken(int seatNo) {
        return seat[seatNo];
    }

    public void bookSeat(int seatNumber){
        this.seat[seatNumber]=true;
    }

}
