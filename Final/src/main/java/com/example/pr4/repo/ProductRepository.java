package com.example.pr4.repo;

import com.example.pr4.models.Product;
import com.example.pr4.models.UserProfile;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends GenericRepository<Product, Long>{
}
