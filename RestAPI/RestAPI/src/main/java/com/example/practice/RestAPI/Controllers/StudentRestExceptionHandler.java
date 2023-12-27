package com.example.practice.RestAPI.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

    @ExceptionHandler
    ResponseEntity<StudentError> handleException(StudentException theStudentException){

        StudentError studentError = new StudentError();
        studentError.setStatus(HttpStatus.NOT_FOUND.value());
        studentError.setMessage(theStudentException.getMessage());
        studentError.setTime(System.currentTimeMillis());

        return new ResponseEntity<>(studentError,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    ResponseEntity<StudentError> handleAllException(Exception exec){
        StudentError studentError = new StudentError();
        studentError.setStatus(HttpStatus.BAD_REQUEST.value());
        studentError.setMessage(exec.getMessage());
        studentError.setTime(System.currentTimeMillis());

        return new ResponseEntity<>(studentError,HttpStatus.BAD_REQUEST);
    }

    }
