package com.example.pr4.controller;

import com.example.pr4.models.UserProfile;
import com.example.pr4.repo.UserProfileRepository;
import com.example.pr4.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();

        var user = userRepository.findByLogin(login).orElseThrow(
                () -> new IllegalArgumentException("Пользователь не найден")
        );

        UserProfile profile = userProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Профиль пользователя не найден"));

        model.addAttribute("profile", profile);
        return "profile/index";
    }

    @GetMapping("/edit")
    public String editProfileForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();

        var user = userRepository.findByLogin(login).orElseThrow(
                () -> new IllegalArgumentException("Пользователь не найден")
        );

        UserProfile profile = userProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Профиль пользователя не найден"));

        model.addAttribute("profile", profile);
        return "profile/edit";
    }

    @PostMapping("/edit")
    public String updateProfile(@ModelAttribute UserProfile profile) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();

        var user = userRepository.findByLogin(login).orElseThrow(
                () -> new IllegalArgumentException("Пользователь не найден")
        );

        profile.setUser(user);
        userProfileRepository.save(profile);
        return "redirect:/profile";
    }
}