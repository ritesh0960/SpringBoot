package com.example.demo.demoapplication.ServiceImpl;


import com.example.demo.demoapplication.Service.Coach;

//making this class as a custom component  without using @Component annotations
public class SwimCoach implements Coach {

//    SwimCoach(){
//        System.out.println("In Constructor : " + getClass().getSimpleName());
//    }
    @Override
    public String getCoach() {
        return "This is a swimming Coach";
    }
}
