package com.example.demo.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.services.exceptions.DatabaseException;
import com.example.demo.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandartError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String err = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandartError error = new StandartError(Instant.now(), status.value(), err, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandartError> database(DatabaseException e, HttpServletRequest request) {
        String err = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandartError error = new StandartError(Instant.now(), status.value(), err, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}