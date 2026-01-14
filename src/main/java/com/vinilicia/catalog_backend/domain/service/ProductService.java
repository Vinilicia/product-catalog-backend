package com.vinilicia.catalog_backend.domain.service;

import com.vinilicia.catalog_backend.domain.model.Product;
import com.vinilicia.catalog_backend.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(String name, int quantity) {
        productRepository.save(new Product(name, quantity));
    }

    public Product getProduct(Long id) {
        return productRepository
            .findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(Long id) {
        productRepository.findById(id).ifPresent(productRepository::delete);
    }
}
