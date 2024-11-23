package com.example.restapi.repo;

import com.example.restapi.models.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends GenericRepository<Order, Long>{
}
