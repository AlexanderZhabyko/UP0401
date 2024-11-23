package com.example.restapi.repo;

import com.example.restapi.models.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends GenericRepository<Role, Long>{
}
