package com.example.ritesh.Service.ExceptionHandler;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(String message) {
        super(message);
    }
}
