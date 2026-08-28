package com.indusmart.controller;

import com.indusmart.dto.CategoryRequest;
import com.indusmart.entity.Category;
import com.indusmart.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Add Category
     */
    @PostMapping("/add")
    public ResponseEntity<String> addCategory(
            @Valid @RequestBody CategoryRequest request) {

        return ResponseEntity.ok(
                categoryService.addCategory(request)
        );
    }

    /**
     * Get All Categories
     */
    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories() {

        return ResponseEntity.ok(
                categoryService.getAllCategories()
        );
    }

    /**
     * Get Category By ID
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(
            @PathVariable Long categoryId) {

        Category category =
                categoryService.getCategoryById(categoryId);

        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(category);
    }

    /**
     * Update Category
     */
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<String> updateCategory(
            @PathVariable Long categoryId,
            @Valid @RequestBody CategoryRequest request) {

        return ResponseEntity.ok(
                categoryService.updateCategory(
                        categoryId,
                        request
                )
        );
    }

    /**
     * Delete Category
     */
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable Long categoryId) {

        return ResponseEntity.ok(
                categoryService.deleteCategory(categoryId)
        );
    }

}