package com.example.practice.MVC.Controllers;

import com.example.practice.MVC.Models.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class studentController {

    @Value("${countries}")
    public List<String> countries;

    @Value("${operating}")
    public List<String> operating;

    @Value("${language}")
    public List<String> language;

    @GetMapping("/showStudentForm")
    public String showStudentForm(Model theModel){
        Student student = new Student();

        theModel.addAttribute("student",student);
        theModel.addAttribute("countries",countries);
        theModel.addAttribute("operating",this.operating);
       // System.out.println(operating);
        theModel.addAttribute("language",this.language);
        return "student-form";
    }
    @PostMapping("/processStudent")
    public String processStudent(@ModelAttribute("student") Student student,Model theModel){
        System.out.println(student.getFirstName()+student.getSecondName());
        theModel.addAttribute("firstName",student.getFirstName());
        theModel.addAttribute("lastName",student.getSecondName());
      //  theModel.addAttribute("country",student.getCountry());
        theModel.addAttribute("country",student.getCountry());
        theModel.addAttribute("operating",student.getOperating());
        theModel.addAttribute("language",student.getLanguage());
        return "student-detail";
    }
}
