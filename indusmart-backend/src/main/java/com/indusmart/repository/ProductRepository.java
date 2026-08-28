package com.indusmart.repository;

import com.indusmart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Company Products
     */
    List<Product> findByCompanyId(Long companyId);

    /**
     * Category
     */
    List<Product> findByCategory(String category);

    /**
     * Brand
     */
    List<Product> findByBrand(String brand);

    /**
     * Search Product Name
     */
    List<Product> findByProductNameContainingIgnoreCase(String productName);

    /**
     * Category excluding current product
     */
    List<Product> findByCategoryAndIdNot(
            String category,
            Long productId
    );

    /**
     * Brand excluding current product
     */
    List<Product> findByBrandAndIdNot(
            String brand,
            Long productId
    );

    /**
     * Products within price range
     */
    List<Product> findByPurchasePriceBetween(
            BigDecimal minPrice,
            BigDecimal maxPrice
    );

    /**
     * Available Products
     */
    List<Product> findByQuantityGreaterThan(Integer quantity);

}