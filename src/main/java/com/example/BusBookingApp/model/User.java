package com.example.BusBookingApp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "firstname",
            length = 20,
            nullable = false
    )
    private String firstName;

    @Column(
            name = "lastname",
            length = 20,
            nullable = false
    )
    private String lastName;

    @Column(
            length = 50,
            nullable = false,
            unique = true
    )
    private String email;

    private String password;

    private Role role;

    private BigDecimal WalletAmount;

    @OneToMany
    private List<Booking> booking;


    public User() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public BigDecimal getWalletAmount() {
        return WalletAmount;
    }

    public void setWalletAmount(BigDecimal walletAmount) {
        WalletAmount = walletAmount;
    }

    public List<Booking> getBooking() {
        return this.booking;
    }

    public void setBooking(List<Booking> booking) {
        this.booking = booking;
    }
}
