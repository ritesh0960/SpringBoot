package com.example.EmployeeCRUD.Controllers;

import com.example.EmployeeCRUD.Entity.User;
import com.example.EmployeeCRUD.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserService userService;

    RegistrationController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model theModel){
        User user = new User();
        theModel.addAttribute("username",user);
        return "registration-form";
    }

    @GetMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("user") User user, Model theModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/register/showRegistrationForm";
        }
        else{
            this.userService.createUser(user);
            return "redirect:/showRegistrationForm";
        }
    }

}
