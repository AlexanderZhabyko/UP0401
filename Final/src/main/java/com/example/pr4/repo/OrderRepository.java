package com.example.pr4.repo;

import com.example.pr4.models.Order;
import com.example.pr4.models.UserProfile;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends GenericRepository<Order, Long>{
}
