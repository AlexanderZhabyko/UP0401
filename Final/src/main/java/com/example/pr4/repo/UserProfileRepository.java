package com.example.pr4.repo;

import com.example.pr4.models.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends GenericRepository<UserProfile, Long> {
    Optional<UserProfile> findByUserId(Long userId);
}
