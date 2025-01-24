package com.example.BusBookingApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.UuidGenerator;
import java.util.UUID;

@Entity
public class Driver {

    @Id
    @UuidGenerator
    private UUID uuid;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false,length = 50)
    private String lastName;

    private String fullName =getFullName();

    @OneToOne
    private Bus bus;

    public String getFullName() {
        return getFirstName()+" "+getLastName();
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        getFullName();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        getFullName();
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}
