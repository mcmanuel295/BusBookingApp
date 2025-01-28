package com.example.BusBookingApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Driver extends User{


    @OneToOne
    private Bus busDriven;

    public Driver(Bus busDriven) {
        super();
        this.busDriven = busDriven;
        setRole(Role.DRIVER);
    }

    public Bus getBusDriven() {
        return busDriven;
    }

    public void setBusDriven(Bus busDriven) {
        this.busDriven = busDriven;
    }

}
