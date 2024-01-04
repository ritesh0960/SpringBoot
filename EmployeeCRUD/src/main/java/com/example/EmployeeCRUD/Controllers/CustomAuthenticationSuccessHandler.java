package com.example.EmployeeCRUD.Controllers;

import com.example.EmployeeCRUD.Entity.User;
import com.example.EmployeeCRUD.Services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private UserService userService;

    @Autowired
    CustomAuthenticationSuccessHandler(UserService userService){
        this.userService=userService;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("In Custom Authentication Success Handler");
        String userName = authentication.getName();
        System.out.println("User Name  "+ userName);
        User theUser = userService.findByUserName(userName);

        //now place in the session
        HttpSession session = request.getSession();
        session.setAttribute("user",theUser);

        //forward to homepage
        response.sendRedirect("/homepage/");

    }
}
