package com.vcube.HospitalManagement.security;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
                                        throws IOException {

        for (GrantedAuthority auth : authentication.getAuthorities()) {

            System.out.println("ROLE: " + auth.getAuthority()); // 🔥 DEBUG

            if (auth.getAuthority().equals("DOCTOR") || 
                auth.getAuthority().equals("ADMIN")) {

                response.sendRedirect("/doctor/dashboard");
                return;
            }
        }

        // Default → Patient
        response.sendRedirect("/dashboard");
    }
}