package com.example.EmployeeCRUD.Controllers;

import com.example.EmployeeCRUD.Services.UserService;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class SecurityConfig {
 // Implementing spring security using hibernate
    //we define a
    //BcryptPasswordEncoder and DaoAuthenticationProvider beans. We assign the
    //UserService and PasswordEncoder to the DaoAuthenticationProvider. These
    //classes are used by Spring Security for custom authentication and authorization.
 //   bcrypt bean definition
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder bcrypt

        return auth;
    }

//   //Authentication using users from database
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//        //for the default user and authority table
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//      /*  for custom user table and roles table
//
//        userDetailsManager
//                .setUsersByUsernameQuery("custom sql auery");
//        userDetailsManager
//                .setAuthoritiesByUsernameQuery("custom sql query");
//
//       */
//
//        return userDetailsManager;
//
//    }

/* Authentication using hard coded users
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
    } */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, AuthenticationSuccessHandler authenticationSuccessHandler) throws Exception{
        httpSecurity.authorizeHttpRequests(configure->
                configure
                        .requestMatchers("/homepage/").permitAll()
                        .requestMatchers("/register/processRegistrationForm").permitAll()
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/homepage/employees")
                        .hasAnyRole("EMPLOYEE","MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/homepage/employees/**")
                        .hasAnyRole("MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/homepage/displayEmployee/**")
                        .hasAnyRole("EMPLOYEE","MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/homepage/displayForm")
                        .hasAnyRole("MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/homepage/updateForm/**")
                        .hasAnyRole("MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.POST,"/homepage/**")
                        .hasAnyRole("MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/homepage/deleteEmployee/**")
                        .hasRole("ADMIN").
                        anyRequest().authenticated()
                ).formLogin(form->
                form.loginPage("/homepage/showLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .successHandler(authenticationSuccessHandler)
                        .permitAll()
                 ).logout(logout->logout
                                       .permitAll()
                                       .logoutSuccessUrl("/homepage/showLoginPage") )
                .exceptionHandling(configurer->
                        configurer.accessDeniedPage("/homepage/accessDenied")
                        );
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
