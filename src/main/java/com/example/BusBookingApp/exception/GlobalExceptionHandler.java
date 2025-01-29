package com.example.BusBookingApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ProblemDetail handleUserNotFoundException(UserNotFoundException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
    }

    @ExceptionHandler
    public ProblemDetail handleBookingNotFoundException(BookingNotFoundException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
    }

    @ExceptionHandler
    public ProblemDetail handleBusNotFoundException(BusNotFoundException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
    }
}
