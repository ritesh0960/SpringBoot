package com.example.thymleaf.thymleafDemo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Controller
public class DemoController {
    @GetMapping("/hello")
    public String demo(Model theModel){
        theModel.addAttribute("theDate", new java.util.Date());
        return "hello";
    }
}
