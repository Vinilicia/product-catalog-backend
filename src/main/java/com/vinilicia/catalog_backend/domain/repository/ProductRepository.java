package com.vinilicia.catalog_backend.domain.repository;

import com.vinilicia.catalog_backend.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
