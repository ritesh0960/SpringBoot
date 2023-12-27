package com.example.demo.demoapplication.ServiceImpl;

import com.example.demo.demoapplication.Service.Coach;
import org.springframework.stereotype.Component;

@Component
public class BasketballCoach implements Coach {

    BasketballCoach(){
        System.out.println("In constructor : " + getClass().getSimpleName());
    }
    @Override
    public String getCoach() {
        return "This is a basketball coach";
    }
}
