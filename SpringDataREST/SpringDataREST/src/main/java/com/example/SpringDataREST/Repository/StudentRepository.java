package com.example.SpringDataREST.Repository;

import com.example.SpringDataREST.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Employee,Integer> {
}
