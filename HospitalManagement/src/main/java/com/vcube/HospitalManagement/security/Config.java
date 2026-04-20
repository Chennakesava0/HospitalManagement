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

    @Autowired
    private LoginSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .userDetailsService(userDetailsService)

            .authorizeHttpRequests(auth -> auth

                // ================= PUBLIC =================
                .requestMatchers(
                    "/",
                    "/login",
                    "/register",
                    "/saveUser",
                    "/css/**",
                    "/js/**",
                    "/images/**"
                ).permitAll()

                // ================= DOCTOR ONLY =================
                .requestMatchers("/doctor/**")
                .hasAuthority("DOCTOR")

                .requestMatchers("/appointments/approve/**")
                .hasAuthority("DOCTOR")

                .requestMatchers("/appointments/reject/**")
                .hasAuthority("DOCTOR")

                // ================= PATIENT RESCHEDULE =================
                // ⭐ FIX: patient should access this
                .requestMatchers("/appointments/reschedule/**")
                .hasAuthority("PATIENT")

                // ================= AUTHENTICATED =================
                .requestMatchers("/dashboard")
                .authenticated()

                // ================= EVERYTHING ELSE =================
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(successHandler)
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

    // ================= PASSWORD ENCODER =================
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}