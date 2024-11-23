package com.example.pr4.controller;

import com.example.pr4.models.Role;
import com.example.pr4.repo.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String index(Model model) {
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        return "roles/index";
    }

    @GetMapping("/new")
    public String newRoleForm(Model model) {
        model.addAttribute("role", new Role());
        return "roles/new";
    }

    @PostMapping
    public String createRole(@Valid @ModelAttribute("role") Role role, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("role", role);
            return "role/new";
        }
        roleRepository.save(role);
        return "redirect:/roles";
    }


    @GetMapping("/{id}/edit")
    public String editRoleForm(@PathVariable Long id, Model model) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid role ID"));
        model.addAttribute("role", role);
        return "roles/edit";
    }

    @PostMapping("/{id}")
    public String updateRole(@PathVariable Long id, @ModelAttribute Role role) {
        role.setId(id);
        roleRepository.save(role);
        return "redirect:/roles";
    }

    @GetMapping("/{id}")
    public String showRole(@PathVariable Long id, Model model) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid role ID"));
        model.addAttribute("role", role);
        return "roles/show";
    }

    @GetMapping("/{id}/delete")
    public String deleteRole(@PathVariable Long id) {
        roleRepository.deleteById(id);
        return "redirect:/roles";
    }
}