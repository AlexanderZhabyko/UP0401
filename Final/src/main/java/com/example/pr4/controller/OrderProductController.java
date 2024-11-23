package com.example.pr4.controller;

import com.example.pr4.models.OrderProduct;
import com.example.pr4.repo.OrderProductRepository;
import com.example.pr4.repo.OrderRepository;
import com.example.pr4.repo.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/OrderProducts")
public class OrderProductController {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String index(Model model) {
        List<OrderProduct> orderProducts = orderProductRepository.findAll();
        model.addAttribute("orderProducts", orderProducts);
        return "OrderProducts/index";
    }

    @GetMapping("/new")
    public String newOrderProductForm(Model model) {
        model.addAttribute("orderProduct", new OrderProduct());
        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        return "OrderProducts/new";
    }

    @PostMapping
    public String createOrderProduct(@Valid @ModelAttribute("orderProduct") OrderProduct orderProduct, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("orderProduct", orderProduct);
            return "OrderProducts/new";
        }
        orderProductRepository.save(orderProduct);
        return "redirect:/OrderProducts";
    }

    @GetMapping("/{id}/edit")
    public String editOrderProductForm(@PathVariable Long id, Model model) {
        OrderProduct orderProduct = orderProductRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order product ID"));
        model.addAttribute("orderProduct", orderProduct);
        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        return "OrderProducts/edit";
    }

    @PostMapping("/{id}")
    public String updateOrderProduct(@PathVariable Long id, @ModelAttribute OrderProduct orderProduct) {
        orderProduct.setId(id);
        orderProductRepository.save(orderProduct);
        return "redirect:/OrderProducts";
    }

    @GetMapping("/{id}")
    public String showOrderProduct(@PathVariable Long id, Model model) {
        OrderProduct orderProduct = orderProductRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order product ID"));
        model.addAttribute("orderProduct", orderProduct);
        return "OrderProducts/show";
    }

    @GetMapping("/{id}/delete")
    public String deleteOrderProduct(@PathVariable Long id) {
        orderProductRepository.deleteById(id);
        return "redirect:/OrderProducts";
    }
}
