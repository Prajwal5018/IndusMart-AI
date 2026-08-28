package com.indusmart.repository;

import com.indusmart.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    /**
     * Get all images of a product
     */
    List<ProductImage> findByProductId(Long productId);

    /**
     * Get primary image of a product
     */
    Optional<ProductImage> findByProductIdAndPrimaryImageTrue(Long productId);

    /**
     * Get images ordered by display order
     */
    List<ProductImage> findByProductIdOrderByDisplayOrderAsc(Long productId);

    /**
     * Delete all images of a product
     */
    void deleteByProductId(Long productId);

}