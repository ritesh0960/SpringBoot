package com.example.demo.demoapplication.ServiceImpl;

import com.example.demo.demoapplication.Service.Coach;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@component annotation makes the class available as a bean
//@Lazy- this class will only be instanized if there is any use of this class in application
//@scope - generally there is only one instance of any class and any object make use of that bean only but can modify by using this annotation
@Component
//@Lazy
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HockeyCoach implements Coach {

    //here we used it for checking @Lazy and @Scope annotation
   HockeyCoach(){
        System.out.println("In Constructor : " + getClass().getSimpleName());
    }

    @Override
    public String getCoach() {
        return "This is a hockey Coach";
    }

    //for checking bean life cycle hooks
    @PostConstruct
    public void checkPostConstruct(){
        System.out.println("In PostConstruct bean life cycle hooks :" + getClass().getSimpleName());
    }
    @PreDestroy
    public void checkPreDestroy(){
        System.out.println("In PreDestroy bean life cycle hooks : " + getClass().getSimpleName());
    }

}
