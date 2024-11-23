package com.example.restapi.controller;

import com.example.restapi.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.restapi.repo.UserProfileRepository;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final UserProfileRepository profileRepository;

    @Autowired
    public ProfileController(UserProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping
    public List<UserProfile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @PostMapping
    public UserProfile createProfile(@RequestBody UserProfile profile) {
        return profileRepository.save(profile);
    }

    @PutMapping("/{id}")
    public UserProfile updateProfile(@PathVariable Long id, @RequestBody UserProfile profile) {
        profile.setId(id);
        return profileRepository.save(profile);
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable Long id) {
        profileRepository.deleteById(id);
    }
}