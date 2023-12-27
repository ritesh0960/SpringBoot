package com.example.demo.demoapplication.ServiceImpl;

import com.example.demo.demoapplication.Service.Coach;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FootballCoach implements Coach {

    FootballCoach(){
        System.out.println("In Constructor : " + getClass().getSimpleName());
    }
    @Override
    public String getCoach() {
        return "This is a Football Coach";
    }
}
