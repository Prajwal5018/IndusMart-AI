package com.indusmart.service;

import com.indusmart.entity.Product;
import com.indusmart.entity.ProductImage;
import com.indusmart.repository.ProductImageRepository;
import com.indusmart.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;
    private final CloudinaryService cloudinaryService;

    public ProductImageService(ProductImageRepository productImageRepository,
                               ProductRepository productRepository,
                               CloudinaryService cloudinaryService) {

        this.productImageRepository = productImageRepository;
        this.productRepository = productRepository;
        this.cloudinaryService = cloudinaryService;
    }

    /**
     * Upload Image to Cloudinary and Save in Database
     */
    public String uploadProductImage(Long productId,
                                     MultipartFile file,
                                     boolean primaryImage,
                                     Integer displayOrder) {

        String imageUrl = cloudinaryService.uploadImage(file);

        return addImage(
                productId,
                imageUrl,
                primaryImage,
                displayOrder
        );
    }

    /**
     * Add Image to Product
     */
    public String addImage(Long productId,
                           String imageUrl,
                           boolean primaryImage,
                           Integer displayOrder) {

        Optional<Product> optionalProduct =
                productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            return "Product not found";
        }

        Product product = optionalProduct.get();

        // Update Product table image
        product.setImageUrl(imageUrl);
        productRepository.save(product);

        // Only one primary image per product
        if (primaryImage) {

            Optional<ProductImage> existingPrimary =
                    productImageRepository
                            .findByProductIdAndPrimaryImageTrue(productId);

            existingPrimary.ifPresent(image -> {
                image.setPrimaryImage(false);
                productImageRepository.save(image);
            });
        }

        ProductImage productImage = ProductImage.builder()
                .product(product)
                .imageUrl(imageUrl)
                .displayOrder(displayOrder)
                .primaryImage(primaryImage)
                .build();

        productImageRepository.save(productImage);

        return "Image Uploaded Successfully";
    }

    /**
     * Get All Images of Product
     */
    public List<ProductImage> getProductImages(Long productId) {

        return productImageRepository
                .findByProductIdOrderByDisplayOrderAsc(productId);
    }

    /**
     * Get Primary Image
     */
    public ProductImage getPrimaryImage(Long productId) {

        return productImageRepository
                .findByProductIdAndPrimaryImageTrue(productId)
                .orElse(null);
    }

    /**
     * Delete Image
     */
    public String deleteImage(Long imageId) {

        Optional<ProductImage> optionalImage =
                productImageRepository.findById(imageId);

        if (optionalImage.isEmpty()) {
            return "Image not found";
        }

        ProductImage image = optionalImage.get();

        // Delete from Cloudinary (optional)
        // cloudinaryService.deleteImage(publicId);

        productImageRepository.delete(image);

        return "Image Deleted Successfully";
    }

    /**
     * Delete All Images of Product
     */
    public String deleteAllImages(Long productId) {

        productImageRepository.deleteByProductId(productId);

        return "All Images Deleted Successfully";
    }

    /**
     * Change Primary Image
     */
    public String setPrimaryImage(Long imageId) {

        Optional<ProductImage> optionalImage =
                productImageRepository.findById(imageId);

        if (optionalImage.isEmpty()) {
            return "Image not found";
        }

        ProductImage selectedImage = optionalImage.get();

        List<ProductImage> images =
                productImageRepository.findByProductId(
                        selectedImage.getProduct().getId()
                );

        for (ProductImage image : images) {

            image.setPrimaryImage(false);

            productImageRepository.save(image);
        }

        selectedImage.setPrimaryImage(true);

        productImageRepository.save(selectedImage);

        return "Primary Image Updated Successfully";
    }
}