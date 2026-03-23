package com.mavi.regioner.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.Date;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalException(Exception ex, WebRequest request) {
        System.out.println("entered global exception handler!");

        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                request.getContextPath()
        );

        return ResponseEntity.internalServerError().body(message);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorMessage> arithmaticException(Exception ex, WebRequest request) {

        System.out.println("Entered arithmatic Exception Handler");

        ErrorMessage message = new ErrorMessage(
                "Cannot divide by 0",
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                request.getDescription(false)
        );

        return ResponseEntity.badRequest().body(message);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                "Could not find resource: " + ex.getResourceName(),
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
