package com.example.BusBookingApp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private final UUID bookingCode = UUID.randomUUID();

    @Column(length = 20,nullable = false)
    private String origin;

    @Column(length = 20,nullable = false)
    private String destination;

    @Column(nullable = false)
    private Integer busNo;

    @Column(nullable = false)
    private Integer seatNo;

    @Future(message = "departure time should always be in the future")
    private LocalDateTime departureDate;

    private BigDecimal cost;

    private boolean hasPaid;

    @ManyToOne
    @JoinColumn(name = "booking")
    private User user;


//    heelpske o
}
