package com.vinilicia.catalog_backend.domain.repository;

import com.vinilicia.catalog_backend.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
