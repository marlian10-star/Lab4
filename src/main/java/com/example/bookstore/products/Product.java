package com.example.bookstore.products;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}