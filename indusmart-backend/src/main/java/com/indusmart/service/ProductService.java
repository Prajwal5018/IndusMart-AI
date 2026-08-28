package com.indusmart.service;

import com.indusmart.dto.ProductRequest;
import com.indusmart.entity.Company;
import com.indusmart.entity.Product;
import com.indusmart.repository.CompanyRepository;
import com.indusmart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;
    private final SubscriptionService subscriptionService;

    public ProductService(ProductRepository productRepository,
                          CompanyRepository companyRepository,
                          SubscriptionService subscriptionService) {

        this.productRepository = productRepository;
        this.companyRepository = companyRepository;
        this.subscriptionService = subscriptionService;
    }

    /**
     * Add New Product
     */
    public String addProduct(ProductRequest request) {

        Optional<Company> companyOptional =
                companyRepository.findById(request.getCompanyId());

        if (companyOptional.isEmpty()) {
            return "Company not found";
        }

        Company company = companyOptional.get();

        // Check Subscription
        if (company.getOwner() == null) {
            return "Company owner not found";
        }

        Long sellerId = company.getOwner().getId();

        if (!subscriptionService.isSubscriptionActive(sellerId)) {
            return "No Active Subscription Found";
        }

        if (!subscriptionService.canUploadProduct(sellerId)) {
            return "Product Upload Limit Reached. Upgrade Your Subscription.";
        }

        Product product = Product.builder()
                .productName(request.getProductName())
                .description(request.getDescription())
                .purchasePrice(request.getPurchasePrice())
                .rentalPricePerDay(request.getRentalPricePerDay())
                .quantity(request.getQuantity())
                .category(request.getCategory())
                .brand(request.getBrand())
                .modelNumber(request.getModelNumber())
                .imageUrl(request.getImageUrl())
                .availableForRent(request.isAvailableForRent())
                .availableForSale(request.isAvailableForSale())
                .company(company)
                .build();

        productRepository.save(product);

        // Increase Product Count
        subscriptionService.incrementUploadedProducts(sellerId);

        return "Product Added Successfully";
    }

    /**
     * Update Product
     */
    public String updateProduct(Long productId,
                                ProductRequest request) {

        Optional<Product> optionalProduct =
                productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            return "Product Not Found";
        }

        Optional<Company> companyOptional =
                companyRepository.findById(request.getCompanyId());

        if (companyOptional.isEmpty()) {
            return "Company not found";
        }

        Product product = optionalProduct.get();

        Company company = companyOptional.get();

        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPurchasePrice(request.getPurchasePrice());
        product.setRentalPricePerDay(request.getRentalPricePerDay());
        product.setQuantity(request.getQuantity());
        product.setCategory(request.getCategory());
        product.setBrand(request.getBrand());
        product.setModelNumber(request.getModelNumber());
        product.setImageUrl(request.getImageUrl());
        product.setAvailableForRent(request.isAvailableForRent());
        product.setAvailableForSale(request.isAvailableForSale());
        product.setCompany(company);

        productRepository.save(product);

        return "Product Updated Successfully";
    }

    /**
     * Get All Products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Get Product By ID
     */
    public Product getProductById(Long id) {

        return productRepository.findById(id).orElse(null);

    }

    /**
     * Get Products By Company
     */
    public List<Product> getProductsByCompany(Long companyId) {

        return productRepository.findByCompanyId(companyId);

    }

    /**
     * Delete Product
     */
    public String deleteProduct(Long productId) {

        Optional<Product> optionalProduct =
                productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            return "Product Not Found";
        }

        Product product = optionalProduct.get();

        Long sellerId =
                product.getCompany().getOwner().getId();

        productRepository.delete(product);

        subscriptionService.decrementUploadedProducts(sellerId);

        return "Product Deleted Successfully";
    }

    /**
     * Search Products By Name
     */
    public List<Product> searchByProductName(String productName) {

        return productRepository
                .findByProductNameContainingIgnoreCase(productName);

    }

    /**
     * Search Products By Category
     */
    public List<Product> searchByCategory(String category) {

        return productRepository.findByCategory(category);

    }

    /**
     * Search Products By Brand
     */
    public List<Product> searchByBrand(String brand) {

        return productRepository.findByBrand(brand);

    }

}