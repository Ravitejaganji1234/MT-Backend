package com.accesshr.emsbackend.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles EmployeeNotFoundException
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEmployeeNotFound(EmployeeNotFoundException e) {
        System.out.println("Error: " + e.getMessage());
        return e.getMessage(); // Return a response message
    }

    // Handles generic exceptions (for unexpected errors)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericException(Exception e) {
        System.out.println("Error: " + e.getMessage());
        return "An internal error occurred"; // Return a default error message
    }

    // This method is triggered when a ResourceNotFoundException is thrown in any controller
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        Map<String, Object> responseBody=new HashMap<>();
        responseBody.put("timestamp", LocalDateTime.now());
        responseBody.put("message", e.getMessage());
        responseBody.put("details", request.getDescription(false));
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }
}



