package com.example.pr4.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotBlank(message = "поле не должно быть пустым!")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotBlank(message = "поле не должно быть пустым")
    private String line;
    @NotBlank(message = "поле не должно быть пустым")
    private String cell;
    @NotBlank(message = "поле не должно быть пустым")
    private Integer quantity;

    public Warehouse() {
    }

    public Warehouse(Long id, Product product, String line, String cell, Integer quantity) {
        this.id = id;
        this.product = product;
        this.line = line;
        this.cell = cell;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
