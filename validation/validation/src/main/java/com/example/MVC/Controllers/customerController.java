package com.example.MVC.Controllers;

import com.example.MVC.Models.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class customerController {

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @GetMapping("/showForm")
    public String showForm(Model theModel){
        Customer customer = new Customer();
        theModel.addAttribute("customer",customer);
        return "customer-form";
    }
    @PostMapping("/processForm")
    public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer,
                              BindingResult theBindingResult){
        System.out.println(theBindingResult);
        if(theBindingResult.hasErrors()){
            return "customer-form";
        }

        else {
            //theModel.addAttribute("firstName",theCustomer.getFirstName());
            //theModel.addAttribute("lastName",theCustomer.getLastName());
            return "customer";
        }
    }
}
