package com.example.EmployeeCRUD.Services;

import com.example.EmployeeCRUD.Entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    public Employee getEmployee(int id);

    public List<Employee> getAllEmployee();

    public Employee createEmployee(Employee employee);
    public Employee updateEmployee(int id,Employee employee);

    public void deleteEmployee(int id);

}
