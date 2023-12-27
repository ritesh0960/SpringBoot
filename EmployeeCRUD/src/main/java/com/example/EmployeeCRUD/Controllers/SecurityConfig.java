package com.example.EmployeeCRUD.Controllers;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails employee = User.builder()
                .username("employee")
                .password("{noop}employee123")
                .roles("EMPLOYEE")
                .build();

        UserDetails manager = User.builder()
                .username("manager")
                .password("{noop}manager123")
                .roles("EMPLOYEE","MANAGER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(employee,manager,admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(configure->
                configure.anyRequest().authenticated()
                ).formLogin(form->
                form.loginPage("/homepage/showLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
                 ).logout(logout->logout.permitAll());
//        httpSecurity.authorizeHttpRequests(configure->
//                configure.requestMatchers(HttpMethod.GET,"/homepage/employees")
//                        .hasRole("EMPLOYEE")
//                        .requestMatchers(HttpMethod.GET,"/homepage/employees/**")
//                        .hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.GET,"/homepage/employees/**")
//                        .hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET,"/homepage/displayEmployee/**")
//                        .hasRole("EMPLOYEE")
//                        .requestMatchers(HttpMethod.GET,"/homepage/displayEmployee/**")
//                        .hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.GET,"/homepage/displayEmployee/**")
//                        .hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET,"/homepage/displayForm")
//                        .hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.GET,"/homepage/displayForm")
//                        .hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET,"/homepage/updateForm/**")
//                        .hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.GET,"/homepage/updateForm/**")
//                        .hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST,"/homepage/**")
//                        .hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.POST,"/homepage/**")
//                        .hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE,"/homepage/**")
//                        .hasRole("ADMIN")
//                );
//        httpSecurity.httpBasic(Customizer.withDefaults());

     //   httpSecurity.csrf(csrf->csrf.disable());

        return httpSecurity.build();
    }
}
