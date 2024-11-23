package com.example.restapi.repo;

import com.example.restapi.models.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends GenericRepository<UserProfile, Long> {
    Optional<UserProfile> findByUserId(Long userId);
}
