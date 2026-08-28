package com.indusmart.controller;

import com.indusmart.entity.ProductImage;
import com.indusmart.service.ProductImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product-image")
@CrossOrigin(origins = "*")
public class ProductImageController {

    private final ProductImageService productImageService;

    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    /**
     * Upload Image to Cloudinary
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam Long productId,
            @RequestParam("file") MultipartFile file,
            @RequestParam(defaultValue = "false") boolean primaryImage,
            @RequestParam(defaultValue = "1") Integer displayOrder) {

        return ResponseEntity.ok(
                productImageService.uploadProductImage(
                        productId,
                        file,
                        primaryImage,
                        displayOrder
                )
        );
    }

    /**
     * Add Image using URL
     */
    @PostMapping("/add")
    public ResponseEntity<String> addImage(
            @RequestParam Long productId,
            @RequestParam String imageUrl,
            @RequestParam(defaultValue = "false") boolean primaryImage,
            @RequestParam(defaultValue = "1") Integer displayOrder) {

        return ResponseEntity.ok(
                productImageService.addImage(
                        productId,
                        imageUrl,
                        primaryImage,
                        displayOrder
                )
        );
    }

    /**
     * Get All Images of Product
     */
    @GetMapping("/{productId}")
    public ResponseEntity<List<ProductImage>> getProductImages(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                productImageService.getProductImages(productId)
        );
    }

    /**
     * Get Primary Image
     */
    @GetMapping("/primary/{productId}")
    public ResponseEntity<ProductImage> getPrimaryImage(
            @PathVariable Long productId) {

        ProductImage image =
                productImageService.getPrimaryImage(productId);

        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(image);
    }

    /**
     * Change Primary Image
     */
    @PutMapping("/primary/{imageId}")
    public ResponseEntity<String> setPrimaryImage(
            @PathVariable Long imageId) {

        return ResponseEntity.ok(
                productImageService.setPrimaryImage(imageId)
        );
    }

    /**
     * Delete One Image
     */
    @DeleteMapping("/{imageId}")
    public ResponseEntity<String> deleteImage(
            @PathVariable Long imageId) {

        return ResponseEntity.ok(
                productImageService.deleteImage(imageId)
        );
    }

    /**
     * Delete All Images of Product
     */
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteAllImages(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                productImageService.deleteAllImages(productId)
        );
    }

}