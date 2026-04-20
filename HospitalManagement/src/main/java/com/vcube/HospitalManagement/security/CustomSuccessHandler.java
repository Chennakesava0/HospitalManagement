package com.vcube.HospitalManagement.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
	public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request,
	                                        HttpServletResponse response,
	                                        Authentication auth)
	            throws IOException {

	        if (auth.getAuthorities().stream()
	                .anyMatch(a -> a.getAuthority().equals("DOCTOR"))) {

	            response.sendRedirect("/doctor/dashboard");

	        } else {
	            response.sendRedirect("/dashboard");
	        }
	    }
	}

