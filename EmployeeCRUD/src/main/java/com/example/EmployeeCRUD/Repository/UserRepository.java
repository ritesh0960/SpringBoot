package com.example.EmployeeCRUD.Repository;

import com.example.EmployeeCRUD.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByUserName(String user);
}
