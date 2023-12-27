package com.example.practice.MVC.Models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class Student {
    private String firstName;
    
    private String secondName;

    private String country;

    private String operating;

    private List<String> language;

    public String getOperating() {
        return operating;
    }

    public void setOperating(String operating) {
        this.operating = operating;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstName(){
        return this.firstName;
    }
    public String getSecondName(){
        return this.secondName;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public void setSecondName(String secondName){
        this.secondName=secondName;
    }
}
