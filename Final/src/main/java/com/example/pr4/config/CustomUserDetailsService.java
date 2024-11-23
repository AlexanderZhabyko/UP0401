package com.example.pr4.config;

import com.example.pr4.models.User;
import com.example.pr4.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с логином " + login + " не найден"));

        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(user.getLogin());
        userBuilder.password(user.getPassword());
        userBuilder.roles(user.getRole().getRoleName());

        return userBuilder.build();
    }
}