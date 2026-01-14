package com.vinilicia.catalog_backend.domain.service;

import com.vinilicia.catalog_backend.domain.model.Category;
import com.vinilicia.catalog_backend.domain.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void createCategory(String name) {
        categoryRepository.save(new Category(name));
    }

    @Transactional(readOnly = true)
    public Category getCategory(Long id) {
        return categoryRepository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException("Category not found")
            );
    }

    @Transactional(readOnly = true)
    public List<Category> getAllCategorys() {
        return categoryRepository.findAll();
    }

    @Transactional
    public void updateCategory(Long id, String name) {
        Category category = categoryRepository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException("Category not found")
            );

        if (name != null) {
            category.rename(name);
        }
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresent(categoryRepository::delete);
    }
}
