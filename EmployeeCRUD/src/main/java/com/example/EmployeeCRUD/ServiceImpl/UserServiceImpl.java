package com.example.EmployeeCRUD.ServiceImpl;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.example.EmployeeCRUD.Entity.Role;
import com.example.EmployeeCRUD.Entity.User;
import com.example.EmployeeCRUD.Repository.RoleRepository;
import com.example.EmployeeCRUD.Repository.UserRepository;
import com.example.EmployeeCRUD.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository,BCryptPasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void createUser(User user) {
        System.out.println("creating the user");
        User newUser = new User();
// assign user details to the user object
        newUser.setUserName(user.getUserName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEnabled(true);
       // System.out.println(newUser.getUserName());
        //System.out.println(newUser.getPassword());
       // newUser.setRoles(Arrays.asList(roleRepository.findByName("ROLE_EMPLOYEE")));
//        user.setFirstName(webUser.getFirstName());
//        user.setLastName(webUser.getLastName());
//        user.setEmail(webUser.getEmail());
        this.userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role->
                new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
