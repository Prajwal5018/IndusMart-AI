package com.indusmart.controller;

import com.indusmart.dto.SellerDashboardDTO;
import com.indusmart.service.SellerDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller/dashboard")
@CrossOrigin(origins = "*")
public class SellerDashboardController {

    private final SellerDashboardService sellerDashboardService;

    public SellerDashboardController(
            SellerDashboardService sellerDashboardService) {

        this.sellerDashboardService = sellerDashboardService;
    }

    /**
     * Seller Dashboard
     */
    @GetMapping("/{companyId}")
    public ResponseEntity<SellerDashboardDTO> getDashboard(
            @PathVariable Long companyId) {

        SellerDashboardDTO dashboard =
                sellerDashboardService.getDashboard(companyId);

        return ResponseEntity.ok(dashboard);
    }

}