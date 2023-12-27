package com.example.ritesh.Service.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentExceptionHandler{

    @ExceptionHandler
    public ResponseEntity<StudentException> handleStudentNotFoundException(StudentNotFoundException studentNotFoundException){
        StudentException studentException = new StudentException();
        studentException.setStatus(HttpStatus.NOT_FOUND.value());
        studentException.setMessage(studentNotFoundException.getMessage());
        studentException.setTime(System.currentTimeMillis());
        return new ResponseEntity<>(studentException,HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler
    public ResponseEntity<StudentException> handleAllStudentException(Exception exception){
        StudentException studentException = new StudentException();
        studentException.setStatus(HttpStatus.BAD_REQUEST.value());
        studentException.setMessage(exception.getMessage());
        studentException.setTime(System.currentTimeMillis());
        return new ResponseEntity<>(studentException,HttpStatus.BAD_REQUEST);
    }

}
