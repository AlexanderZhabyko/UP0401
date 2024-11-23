package com.example.pr4.repo;

import com.example.pr4.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    List<OrderProduct> findByOrderId(Long orderId);
    List<OrderProduct> findByProductId(Long productId);
}
