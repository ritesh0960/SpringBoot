package com.example.EmployeeCRUD.Services;

import com.example.EmployeeCRUD.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User findByUserName(String userName);

    public void createUser(User user);

}
