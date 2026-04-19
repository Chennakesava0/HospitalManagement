package com.vcube.HospitalManagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Config {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            // ✅ Set custom user details service
            .userDetailsService(userDetailsService)

            .authorizeHttpRequests(auth -> auth
            	    .requestMatchers(
            	        "/", 
            	        "/login",
            	        "/register",
            	        "/saveUser",
            	        "/css/**",
            	        "/js/**",
            	        "/images/**"
            	    ).permitAll()

            	    .requestMatchers("/dashboard").authenticated()

            	    // 🔥 Protect reschedule
            	    .requestMatchers("/appointments/reschedule/**")
            	    .hasAnyRole("DOCTOR","ADMIN")
            	    .anyRequest().authenticated()
            	)
            
                .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")

                // ✅ After login go to dashboard
                .defaultSuccessUrl("/dashboard", true)

                .failureUrl("/login?error=true")
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
            );

        return http.build();
    }

    // ✅ Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}