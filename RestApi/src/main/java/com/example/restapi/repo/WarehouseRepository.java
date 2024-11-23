package com.example.restapi.repo;

import com.example.restapi.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    List<Warehouse> findByProductId(Long productId);
}
