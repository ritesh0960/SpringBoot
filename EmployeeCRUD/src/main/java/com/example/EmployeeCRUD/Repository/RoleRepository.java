package com.example.EmployeeCRUD.Repository;

import com.example.EmployeeCRUD.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    public Role findByName(String role);
}
