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

    @ManyToMany
    @JoinTable(
        name = "product_person",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> owners = new HashSet<>();

    protected Product() {}

    public Product(String name, int quantity) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

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
        return Set.copyOf(categories);
    }

    public Set<Person> getOwners() {
        return Set.copyOf(owners);
    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }
        this.name = newName;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeLocation(String local) {
        this.local = local;
    }

    public void changeImage(String imagePath) {
        this.imagePath = imagePath;
    }

    public void increaseStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.quantity += amount;
    }

    public void decreaseStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (this.quantity - amount < 0) {
            throw new IllegalStateException("Insufficient stock");
        }
        this.quantity -= amount;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
        category.addProduct(this);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
        category.removeProduct(this);
    }

    public void addOwner(Person person) {
        this.owners.add(person);
        person.addProduct(this);
    }

    public void removeOwner(Person person) {
        this.owners.remove(person);
        person.removeProduct(this);
    }
}
