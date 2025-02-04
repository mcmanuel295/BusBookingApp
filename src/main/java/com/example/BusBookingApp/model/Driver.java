package com.example.BusBookingApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    @Column(
            name = "firstName",
            length = 20,
            nullable = false
    )
    private String firstName;

    @Column(
            name = "lastName",
            length = 20,
            nullable = false
    )
    private String lastName;

    @Column(
            length = 50,
            nullable = false,
            unique = true
    )
    private String fullName =getFullName();

    @Email
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    public Driver() {
        super();
        setRole(Role.DRIVER);
    }


    public void setFullName() {
        fullName = this.firstName +" "+this.lastName;
    }


}
