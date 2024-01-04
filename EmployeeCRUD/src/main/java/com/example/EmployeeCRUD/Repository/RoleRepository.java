package com.example.EmployeeCRUD.Repository;

import com.example.EmployeeCRUD.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
