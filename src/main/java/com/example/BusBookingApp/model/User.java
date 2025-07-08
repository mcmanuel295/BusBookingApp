package com.example.BusBookingApp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

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
    private String fullName =fullName();

    @Email(message = "email must have the correct format")
    private String email;

    @NonNull
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private BigDecimal walletAmount;

    @OneToMany
    private List<Booking> booking;

    private String fullName(){
        return  getLastName()+" "+getFirstName();
    }

<<<<<<< HEAD
=======
    public BigDecimal withdraw(BigDecimal amount){
        if (walletAmount.compareTo(BigDecimal.valueOf(0)) <0 || walletAmount.compareTo(amount)<0 ) {
            throw new RuntimeException("Invalid transaction");
        }

        walletAmount =walletAmount.subtract(amount);
        this.setWalletAmount(walletAmount);
        return walletAmount;
    }
>>>>>>> f0ac1cd9a228c3c8b560a8d4a325143ab3c68480
}
