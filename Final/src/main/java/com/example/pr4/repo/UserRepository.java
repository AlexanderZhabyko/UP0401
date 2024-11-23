package com.example.pr4.repo;

import com.example.pr4.models.User;
import com.example.pr4.models.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericRepository<User, Long>{
    Optional<User> findByLogin(String login);
}
