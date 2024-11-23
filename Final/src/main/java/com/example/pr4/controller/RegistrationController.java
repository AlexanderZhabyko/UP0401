package com.example.pr4.controller;

import com.example.pr4.models.RegistrationForm;
import com.example.pr4.models.User;
import com.example.pr4.models.UserProfile;
import com.example.pr4.repo.RoleRepository;
import com.example.pr4.repo.UserProfileRepository;
import com.example.pr4.repo.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired 
    private UserProfileRepository userProfileRepository;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(
            @Valid @ModelAttribute("registrationForm") RegistrationForm form,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "registration";
        }

        if (!form.getPassword().equals(form.getConfirmPassword())) {
            model.addAttribute("error", "Пароли не совпадают");
            return "registration";
        }

        if (userRepository.findByLogin(form.getLogin()).isPresent()) {
            model.addAttribute("error", "Пользователь с таким логином уже существует");
            return "registration";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setLogin(form.getLogin());
        user.setPassword(encoder.encode(form.getPassword()));
        user.setEmail(form.getEmail());
        user.setRole(roleRepository.findById(2L)
                .orElseThrow(() -> new IllegalArgumentException("Роль клиента не найдена")));
        userRepository.save(user);

        UserProfile profile = new UserProfile();
        profile.setAddress(form.getAddress());
        profile.setPhone(form.getPhone());
        profile.setUser(user);
        userProfileRepository.save(profile);

        return "redirect:/login";
    }
}