package com.example.restapi.repo;

import com.example.restapi.models.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends GenericRepository<Product, Long>{
}
