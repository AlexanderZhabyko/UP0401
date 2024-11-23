package com.example.pr4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(requests -> requests
                        .requestMatchers("/", "/home", "/registration", "/register").permitAll()
                        .requestMatchers("/warehouse/**", "/orders/").hasAuthority("ROLE_warehouse")
                        .requestMatchers("/orders", "/profile/**", "/orders/new").hasAnyAuthority("ROLE_admin", "ROLE_user")
                        .requestMatchers("/orders/**", "/warehouse/**", "/OrderProducts/**", "/products/**", "/profile/**", "/roles/**", "/suppliers/**", "/users/**").hasAuthority("ROLE_admin")
                        .requestMatchers("/orders", "/orders/new", "/products/**", "/profile/**").hasAnyAuthority("ROLE_user")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .usernameParameter("login")
                        .successHandler((request, response, authentication) -> {
                            response.sendRedirect("/orders");
                        })
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}