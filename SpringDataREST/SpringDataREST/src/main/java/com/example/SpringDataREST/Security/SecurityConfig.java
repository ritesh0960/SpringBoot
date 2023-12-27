package com.example.SpringDataREST.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

 /*   @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails ritesh= User.builder()
                .username("ritesh")
                .password("{noop}ritesh")
                .roles("STUDENT")
                .build();

        UserDetails raushan = User.builder()
                .username("raushan")
                .password("{noop}raushan")
                .roles("STUDENT","PROFESSOR")
                .build();
        UserDetails riteshraushan = User.builder()
                .username("riteshraushan")
                .password("{noop}riteshraushan")
                .roles("STUDENT","PROFESSOR","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(ritesh,raushan,riteshraushan);
    }

  */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers(HttpMethod.GET,"/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET,"/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST,"/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT,"/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/employees/**").hasRole("ADMIN")
                );

        //use http basic authentication

        http.httpBasic(Customizer.withDefaults());

        //disable cross site request forgery (CSRF)
        //in general not required for stateless RESTAPIs that uses POST,PUT,PATCH,GET,DELETE
        http.csrf(csrf->csrf.disable());

        return http.build();

    }

}
