package com.example.demo.demoapplication.Controllers;

import com.example.demo.demoapplication.Service.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    private Coach coach;

    //constructor injection
//    @Autowired
//    FunRestController(@Qualifier("hockeyCoach")Coach coach,
//                      @Qualifier("hockeyCoach")Coach theAnotherCoach){
//        System.out.println("In constructor : " + getClass().getSimpleName());
//        this.coach=coach;
//    }


    @Autowired
    FunRestController(@Qualifier("swimCoach")Coach coach){
        System.out.println("In constructor : " + getClass().getSimpleName());
        this.coach=coach;
    }


    //Setter injection


//    @Autowired
//    public void setCoach(Coach coach) {
//        this.coach = coach;
//    }

    @Value("${student.name}")
    private String name;

    @Value("${student.prof}")
    private String prof;
    @GetMapping("/")
    public String funRest(){
        return "Hello world! I am there";
    }

    @GetMapping("/studentInfo")
    public String studentInfo(){
        return this.name + " " + this.prof;
    }
    @GetMapping("/getCoach")
    public String getCoach(){
        return this.coach.getCoach();
    }
}
