package com.example.EmployeeCRUD.Controllers;

import com.example.EmployeeCRUD.Entity.Employee;
import com.example.EmployeeCRUD.Services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collection;

@Controller
@RequestMapping("/homepage")
public class EmployeeViewController {


    EmployeeService employeeService;

    @Autowired
    EmployeeViewController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String commonPage(){
        return "common-page";
    }
    @GetMapping("/showLoginPage")
    public String showloginForm(){
        return "login-form";
    }
    @GetMapping("/employees")
    public String getHome(Model theModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        theModel.addAttribute("username",userName);
        theModel.addAttribute("employees", this.employeeService.getAllEmployee());
        return "homepage";
    }

    @GetMapping("/displayForm")
    public String displayForm(Model theModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Employee employee = new Employee();
        theModel.addAttribute("username",userName);
        theModel.addAttribute("employee", employee);
        return "employee-form";
    }

    @GetMapping("displayEmployee/{id}")
    public String displayEmployee(@PathVariable int id, Model theModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println(authorities);
        Employee employee = this.employeeService.getEmployee(id);
        theModel.addAttribute("firstName", employee.getFirstName());
        theModel.addAttribute("lastName", employee.getLastName());
        theModel.addAttribute("email", employee.getEmail());
        theModel.addAttribute("id", employee.getId());
        theModel.addAttribute("username",userName);
        theModel.addAttribute("role",authorities);
        return "display-employee";
    }

    @PostMapping("/processForm")
    public String createEmployee(@Valid @ModelAttribute("employee") Employee theEmployee, Model theModel,
                                 BindingResult bindingResult) {
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            //return "redirect:/homepage/displayForm";
            System.out.println("This is inside if block :" + bindingResult);
            return "employee-form";
        } else {
            Employee employee = this.employeeService.createEmployee(theEmployee);
//              theModel.addAttribute("firstName",employee.getFirstName());
//              theModel.addAttribute("lastName",employee.getLastName());
//              theModel.addAttribute("email",employee.getEmail());
//              theModel.addAttribute("id",employee.getId());
            System.out.println("This is from else block");
            return "redirect:/homepage/employees";
        }
    }

    @GetMapping("/updateForm/{id}")
    public String updateEmployeeForm(Model theModel,
                                     @PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        System.out.println("id fetched" + id);
        Employee employee = this.employeeService.getEmployee(id);
        theModel.addAttribute("employee",employee);
        theModel.addAttribute("username",userName);

        return "update-form";
    }

    @PostMapping("/updateEmployee/{id}")
    public String updateEmployee(@Valid @ModelAttribute("employee") Employee employee,
                                 @PathVariable int id,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "redirect:/homepage";
        }
        this.employeeService.updateEmployee(id, employee);
        return "redirect:/homepage/employees";
    }

    @GetMapping("deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        this.employeeService.deleteEmployee(id);
        return "redirect:/homepage/employees";
    }
    @GetMapping("/accessDenied")
    public String accessDeniedPage(){
        return "access-denied";
    }
}
