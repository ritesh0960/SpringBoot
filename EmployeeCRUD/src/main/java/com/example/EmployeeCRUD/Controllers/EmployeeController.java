package com.example.EmployeeCRUD.Controllers;

import com.example.EmployeeCRUD.Entity.Employee;
import com.example.EmployeeCRUD.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api")
public class EmployeeController {
    EmployeeService  theEmployeeService;
    @Autowired
    EmployeeController(EmployeeService employeeService){
        theEmployeeService=employeeService;
    }

    @GetMapping("/hello")
    public String getHello(){
        return "Its working!";
    }
    @GetMapping("/getEmployee/{id}")
    public Employee getEmployee(@PathVariable int id){
       Employee employee = theEmployeeService.getEmployee(id);
       return employee;
    }
    @GetMapping("getAllEmployee")
    public List<Employee> getAllEmployee(){
        return  this.theEmployeeService.getAllEmployee();
    }

    @PostMapping("/createEmployee")
    public Employee createEmployee(@RequestBody Employee employee){
        return this.theEmployeeService.createEmployee(employee);
    }
    @PutMapping("/updateEmployee/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee){
        return this.theEmployeeService.updateEmployee(id, employee);
    }
    @DeleteMapping("/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable int id){
        this.theEmployeeService.deleteEmployee(id);
    }




}
