package com.vinilicia.catalog_backend.domain.service;

import com.vinilicia.catalog_backend.domain.model.Product;
import com.vinilicia.catalog_backend.domain.repository.CategoryRepository;
import com.vinilicia.catalog_backend.domain.repository.PersonRepository;
import com.vinilicia.catalog_backend.domain.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void createProduct(String name, int quantity) {
        productRepository.save(new Product(name, quantity));
    }

    @Transactional(readOnly = true)
    public Product getProduct(Long id) {
        return productRepository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException("Product not found")
            );
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void updateProduct(
        Long id,
        String name,
        String description,
        String imagePath,
        int stockDelta,
        String local
    ) {
        Product product = productRepository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException("Product not found")
            );

        if (name != null) {
            product.rename(name);
        }

        if (description != null) {
            product.changeDescription(description);
        }

        if (local != null) {
            product.changeLocation(local);
        }

        if (stockDelta > 0) {
            product.increaseStock(stockDelta);
        } else if (stockDelta < 0) {
            product.decreaseStock(-stockDelta);
        }
    }

    @Transactional
    public void addCategoryToProduct(Long productId, Long categoryId) {
        Product product = productRepository
            .findById(productId)
            .orElseThrow(() ->
                new EntityNotFoundException("Product not found")
            );

        Category category = categoryRepository
            .findById(categoryId)
            .orElseThrow(() ->
                new EntityNotFoundException("Category not found")
            );
        product.addCategory(category);
    }

    @Transactional
    public void removeCategoryFromProduct(Long productId, Long categoryId) {
        Product product = productRepository
            .findById(productId)
            .orElseThrow(() ->
                new EntityNotFoundException("Product not found")
            );

        Category category = categoryRepository
            .findById(categoryId)
            .orElseThrow(() ->
                new EntityNotFoundException("Category not found")
            );

        product.removeCategory(category);
    }

    @Transactional
    public void addOwnerToProduct(Long productId, Long personId) {
        Product product = productRepository
            .findById(productId)
            .orElseThrow(() ->
                new EntityNotFoundException("Product not found")
            );

        Person person = personRepository
            .findById(personId)
            .orElseThrow(() -> new EntityNotFoundException("Person not found"));

        product.addOwner(person);
    }

    @Transactional
    public void removeOwnerFromProduct(Long productId, Long personId) {
        Product product = productRepository
            .findById(productId)
            .orElseThrow(() ->
                new EntityNotFoundException("Product not found")
            );

        Person person = personRepository
            .findById(personId)
            .orElseThrow(() -> new EntityNotFoundException("Person not found"));

        product.removeOwner(person);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.findById(id).ifPresent(productRepository::delete);
    }
}
