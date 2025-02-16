package com.accesshr.emsbackend.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor
@Data
public class ResourceNotFoundException extends RuntimeException{

    // Message to store the custom error message
    private String message;
    private HashMap<String, String> map;
    private Boolean isLeavesCompleted;

    // Constructor accepting a single message parameter
    public ResourceNotFoundException(String message) {
        this.message = message;
    }

    // Constructor accepting two message parameters:
    // one for the super class and another to set the custom message
    public ResourceNotFoundException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public ResourceNotFoundException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public ResourceNotFoundException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
    }

    public ResourceNotFoundException(HashMap<String, String> map) {
        this.map=map;
    }

    public ResourceNotFoundException(Boolean isLeavesCompleted, String message) {
        this.isLeavesCompleted=isLeavesCompleted;
        this.message = message;
        map=new HashMap<>();
        map.put("message", message);
        map.put("isLeavesCompleted", isLeavesCompleted.toString());
    }

    public ResourceNotFoundException(String message, HashMap<String, String> map, Boolean isLeavesCompleted) {
        this.message = message;
        this.map = map;
        this.isLeavesCompleted = isLeavesCompleted;
    }

    public ResourceNotFoundException(String message, String message1, HashMap<String, String> map, Boolean isLeavesCompleted) {
        super(message);
        this.message = message1;
        this.map = map;
        this.isLeavesCompleted = isLeavesCompleted;
    }
}
