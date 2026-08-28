package com.indusmart.controller;

import com.indusmart.dto.ProductRecommendationDTO;
import com.indusmart.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin(origins = "*")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(
            RecommendationService recommendationService) {

        this.recommendationService = recommendationService;
    }

    /**
     * Recommend Products Similar To Current Product
     */
    @GetMapping("/{productId}")
    public ResponseEntity<List<ProductRecommendationDTO>> recommendProducts(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                recommendationService.recommendProducts(productId)
        );
    }

}