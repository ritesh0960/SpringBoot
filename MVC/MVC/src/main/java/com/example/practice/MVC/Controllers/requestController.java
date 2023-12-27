package com.example.practice.MVC.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class requestController {

    //displaying the form to the user
    @RequestMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }
    //processing the form to send the response version1
    @RequestMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    //processing the form to send the response version2
    @RequestMapping("/processFormVersion2")
    public String processFormv2(HttpServletRequest http, Model model){
        String theName = http.getParameter("studentName");
        theName = theName.toUpperCase();
        String result = "There is a grand welcome  " +  theName + "  !!";
        model.addAttribute("message",result);
        return "helloworld";
    }

    //processing the form to send the response version3

    @RequestMapping("/processFormVersion3")
    public String processFormv3(@RequestParam("studentName") String theName,Model model){
        theName = theName.toUpperCase();
        String result = "There is a grand welcome  " +  theName + "  !! from version 3";
        model.addAttribute("message",result);
        return "helloworld";
    }

//    @GetMapping("/studentForm")
//    public String getStudentForm(){
//        return "student-form";
//    }

}
