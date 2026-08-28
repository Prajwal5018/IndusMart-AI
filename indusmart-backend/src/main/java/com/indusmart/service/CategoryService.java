package com.indusmart.service;

import com.indusmart.dto.CategoryRequest;
import com.indusmart.entity.Category;
import com.indusmart.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Add Category
     */
    public String addCategory(CategoryRequest request) {

        if (categoryRepository.existsByCategoryName(request.getCategoryName())) {
            return "Category Already Exists";
        }

        Category category = Category.builder()
                .categoryName(request.getCategoryName())
                .description(request.getDescription())
                .build();

        categoryRepository.save(category);

        return "Category Added Successfully";
    }

    /**
     * Get All Categories
     */
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();

    }

    /**
     * Get Category By ID
     */
    public Category getCategoryById(Long categoryId) {

        return categoryRepository.findById(categoryId).orElse(null);

    }

    /**
     * Update Category
     */
    public String updateCategory(Long categoryId,
                                 CategoryRequest request) {

        Optional<Category> optionalCategory =
                categoryRepository.findById(categoryId);

        if (optionalCategory.isEmpty()) {
            return "Category Not Found";
        }

        Category category = optionalCategory.get();

        if (!category.getCategoryName().equalsIgnoreCase(request.getCategoryName())
                && categoryRepository.existsByCategoryName(request.getCategoryName())) {

            return "Category Already Exists";
        }

        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        categoryRepository.save(category);

        return "Category Updated Successfully";
    }

    /**
     * Delete Category
     */
    public String deleteCategory(Long categoryId) {

        Optional<Category> optionalCategory =
                categoryRepository.findById(categoryId);

        if (optionalCategory.isEmpty()) {
            return "Category Not Found";
        }

        categoryRepository.delete(optionalCategory.get());

        return "Category Deleted Successfully";
    }

}