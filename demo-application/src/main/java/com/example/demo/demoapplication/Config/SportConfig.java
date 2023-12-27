package com.example.demo.demoapplication.Config;

import com.example.demo.demoapplication.Service.Coach;
import com.example.demo.demoapplication.ServiceImpl.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
