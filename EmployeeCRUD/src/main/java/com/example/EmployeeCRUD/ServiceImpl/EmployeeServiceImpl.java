package com.example.EmployeeCRUD.ServiceImpl;

import com.example.EmployeeCRUD.Controllers.EmployeeController;
import com.example.EmployeeCRUD.Entity.Employee;
import com.example.EmployeeCRUD.Repository.EmployeeRepository;
import com.example.EmployeeCRUD.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    @Autowired
    EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){
        this.employeeRepository=theEmployeeRepository;
    }
    @Override
    public Employee getEmployee(int id) {
        Employee theEmployee;
       // employee.
        Optional<Employee> employee =  this.employeeRepository.findById(id);
        if(employee.isPresent()){
            theEmployee=employee.get();
        }
        else {
            throw new RuntimeException("Employee is not present with user id "+id);
        }
        return theEmployee;
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> theEmployee =this.employeeRepository.findAll();
         return theEmployee;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        Employee theEmployee = new Employee();
        theEmployee.setFirstName(employee.getFirstName());
        theEmployee.setLastName(employee.getLastName());
        theEmployee.setEmail(employee.getEmail());
        this.employeeRepository.save(theEmployee);
        return theEmployee;
    }

    @Override
    public Employee updateEmployee(int id,Employee employee) {
        Employee theEmployee = new Employee();
        Optional<Employee> tempEmployee = this.employeeRepository.findById(id);
        if(tempEmployee.isPresent()){
            theEmployee=tempEmployee.get();
            theEmployee.setFirstName(employee.getFirstName());
            theEmployee.setLastName(employee.getLastName());
            theEmployee.setEmail(employee.getEmail());
           this.employeeRepository.save(theEmployee);
        }
        else {
            throw new RuntimeException("Employee is not present with the id "+ id);
        }
        return theEmployee;
    }

    @Override
    public void deleteEmployee(int id) {
     Optional<Employee> employee = this.employeeRepository.findById(id);
     if(employee.isPresent()){
         this.employeeRepository.deleteById(id);
     }else {
         throw new RuntimeException("Employee is not present with user id "+id);
     }
    }
}
