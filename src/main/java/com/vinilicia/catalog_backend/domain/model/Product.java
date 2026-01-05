package com.vinilicia.catalog_backend.domain.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "imagePath", nullable = true)
    private String imagePath;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "local", nullable = true)
    private String local;

    @ManyToMany
    @JoinTable(
        name = "product_category",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    protected Product() {}

    public Product(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getLocal() {
        return local;
    }

    public Set<Category> getCategories() {
        return categories;
    }
}
