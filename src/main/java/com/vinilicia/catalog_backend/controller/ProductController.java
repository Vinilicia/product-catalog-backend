package com.vinilicia.catalog_backend.controller;

import com.vinilicia.catalog_backend.domain.model.Product;
import com.vinilicia.catalog_backend.domain.service.ProductService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(
        @RequestParam String name,
        @RequestParam int quantity
    ) {
        productService.createProduct(name, quantity);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(
        @PathVariable Long id,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String description,
        @RequestParam(required = false) String imagePath,
        @RequestParam(required = false, defaultValue = "0") int stockDelta,
        @RequestParam(required = false) String local
    ) {
        productService.updateProduct(
            id,
            name,
            description,
            imagePath,
            stockDelta,
            local
        );
    }

    @PostMapping("/{productId}/categories/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCategory(
        @PathVariable Long productId,
        @PathVariable Long categoryId
    ) {
        productService.addCategoryToProduct(productId, categoryId);
    }

    @DeleteMapping("/{productId}/categories/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCategory(
        @PathVariable Long productId,
        @PathVariable Long categoryId
    ) {
        productService.removeCategoryFromProduct(productId, categoryId);
    }

    @PostMapping("/{productId}/owners/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addOwner(
        @PathVariable Long productId,
        @PathVariable Long personId
    ) {
        productService.addOwnerToProduct(productId, personId);
    }

    @DeleteMapping("/{productId}/owners/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeOwner(
        @PathVariable Long productId,
        @PathVariable Long personId
    ) {
        productService.removeOwnerFromProduct(productId, personId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
