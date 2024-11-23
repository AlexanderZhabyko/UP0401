package com.example.pr4.repo;

import com.example.pr4.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    List<Warehouse> findByProductId(Long productId);
}
