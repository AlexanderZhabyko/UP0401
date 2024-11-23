package com.example.restapi.repo;

import com.example.restapi.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericRepository<User, Long>{
    Optional<User> findByLogin(String login);
}
