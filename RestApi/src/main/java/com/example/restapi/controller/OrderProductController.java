package com.example.restapi.controller;

import com.example.restapi.models.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.restapi.repo.OrderProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api/orderproducts")
public class OrderProductController {

    private final OrderProductRepository orderProductRepository;

    @Autowired
    public OrderProductController(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @GetMapping
    public List<OrderProduct> getAllOrderProducts() {
        return orderProductRepository.findAll();
    }

    @PostMapping
    public OrderProduct createOrderProduct(@RequestBody OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }

    @PutMapping("/{id}")
    public OrderProduct updateOrderProduct(@PathVariable Long id, @RequestBody OrderProduct orderProduct) {
        orderProduct.setId(id);
        return orderProductRepository.save(orderProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderProduct(@PathVariable Long id) {
        orderProductRepository.deleteById(id);
    }
}