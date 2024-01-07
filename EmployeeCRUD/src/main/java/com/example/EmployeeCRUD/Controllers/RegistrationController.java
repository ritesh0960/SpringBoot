package com.example.EmployeeCRUD.Controllers;

import com.example.EmployeeCRUD.Entity.User;
import com.example.EmployeeCRUD.Services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserService userService;

    @Autowired
    RegistrationController(UserService userService){
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }


    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model theModel){
        User user = new User();
        theModel.addAttribute("user",user);
        return "registration-form";
    }

  //  @PostMapping("/processRegistrationForm")
   // public String processRegistrationForm(@Valid @ModelAttribute("user") User user, Model theModel, BindingResult bindingResult, HttpSession session){
//        String userName = user.getUserName();
//        System.out.println("creating the user " + userName);
//
//        //form validation
//        if(bindingResult.hasErrors()){
//            return "redirect:/register/showRegistrationForm";
//        }
//        //check database if the user already exist
//
//        User existingUser = userService.findByUserName(userName);
//        if(existingUser!=null){
//            theModel.addAttribute("user",new User());
//            theModel.addAttribute("registrationError","User is already present");
//            return "registration-form";
//        }
//        //create new user and store in the database
//        userService.createUser(user);
//        session.setAttribute("user",user);
//        return "RegistrationSuccessful";

  //      this.userService.createUser(user);
    //    return "redirect:/homepage/";
   // }

    @PostMapping("/processRegistrationForm")
    public String createUser(@Valid @ModelAttribute("user") User user, Model theModel, HttpSession session, BindingResult bindingResult){
        String userName = user.getUserName();
        System.out.println("we are creating the user " + userName);
        System.out.println(bindingResult);

        //form Validation
        if(bindingResult.hasErrors()){
            System.out.println("inside binding result error");
           // theModel.addAttribute("user",user);
            return "registration-form";
        }
        //finding user in the database if its already existing
        User oldUser = userService.findByUserName(userName);
        if(oldUser!=null){
            System.out.println("old user is not null");
            theModel.addAttribute("user",new User());
            theModel.addAttribute("error","User Already exists");
            return "registration-form";
        }
        //creating the user in the database
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        userService.createUser(user);
        session.setAttribute("user",user);
        return "redirect:/homepage/showLoginPage";
    }

}
