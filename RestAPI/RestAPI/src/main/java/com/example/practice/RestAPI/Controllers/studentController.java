package com.example.practice.RestAPI.Controllers;

import com.example.practice.RestAPI.Entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class studentController {

    List<Student> theStudent;
    @PostConstruct
    public void assignStudent(){
        theStudent = new ArrayList<>();
        theStudent.add(new Student("ritesh","raushan","riteshraushan0960@gmail.com"));
        theStudent.add(new Student("ankit","Singh","ankit2899@gmail.com"));
        theStudent.add(new Student("abhijeet","acharya","abhiheet123@gmail.com"));
    }
    @GetMapping("/student")
    public List<Student> student(){
        return theStudent;
    }
    @GetMapping("/student/{studentID}")
    public Student getOneStudent(@PathVariable int studentID){

        if(studentID>=theStudent.size() || studentID<0){
            throw  new StudentException("Student does not exists with ID  " + studentID);
        }

        return theStudent.get(studentID);
    }


}
