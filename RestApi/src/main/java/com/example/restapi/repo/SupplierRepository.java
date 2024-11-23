package com.example.restapi.repo;

import com.example.restapi.models.Supplier;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends GenericRepository<Supplier, Long>{
}
