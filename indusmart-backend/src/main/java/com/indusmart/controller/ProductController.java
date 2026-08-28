package com.indusmart.controller;

import com.indusmart.dto.ProductRequest;
import com.indusmart.entity.Product;
import com.indusmart.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Add Product
     */
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(
            @Valid @RequestBody ProductRequest request) {

        return ResponseEntity.ok(
                productService.addProduct(request)
        );
    }

    /**
     * Update Product
     */
    @PutMapping("/update/{productId}")
    public ResponseEntity<String> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody ProductRequest request) {

        return ResponseEntity.ok(
                productService.updateProduct(productId, request)
        );
    }

    /**
     * Delete Product
     */
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                productService.deleteProduct(productId)
        );
    }

    /**
     * Get All Products
     */
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {

        return ResponseEntity.ok(
                productService.getAllProducts()
        );
    }

    /**
     * Get Product By ID
     */
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(
            @PathVariable Long productId) {

        Product product = productService.getProductById(productId);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    /**
     * Get Products By Company
     */
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Product>> getProductsByCompany(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(
                productService.getProductsByCompany(companyId)
        );
    }

    /**
     * Search Product By Name
     */
    @GetMapping("/search/name")
    public ResponseEntity<List<Product>> searchByName(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                productService.searchByProductName(keyword)
        );
    }

    /**
     * Search Product By Brand
     */
    @GetMapping("/search/brand")
    public ResponseEntity<List<Product>> searchByBrand(
            @RequestParam String brand) {

        return ResponseEntity.ok(
                productService.searchByBrand(brand)
        );
    }

    /**
     * Search Product By Category
     */
    @GetMapping("/search/category")
    public ResponseEntity<List<Product>> searchByCategory(
            @RequestParam String category) {

        return ResponseEntity.ok(
                productService.searchByCategory(category)
        );
    }

}