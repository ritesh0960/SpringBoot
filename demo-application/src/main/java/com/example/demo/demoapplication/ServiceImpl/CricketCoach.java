package com.example.demo.demoapplication.ServiceImpl;

import com.example.demo.demoapplication.Service.Coach;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class CricketCoach implements Coach {

    //just for checking lazy insitialization

    CricketCoach(){
        System.out.println("In Constructor : " + getClass().getSimpleName());
    }
    @Override
    public String getCoach() {
        return "Hello this is cricket coach";
    }
}
