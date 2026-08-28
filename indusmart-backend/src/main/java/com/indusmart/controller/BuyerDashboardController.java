package com.indusmart.controller;

import com.indusmart.dto.BuyerDashboardDTO;
import com.indusmart.service.BuyerDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buyer/dashboard")
@CrossOrigin(origins = "*")
public class BuyerDashboardController {

    private final BuyerDashboardService buyerDashboardService;

    public BuyerDashboardController(
            BuyerDashboardService buyerDashboardService) {

        this.buyerDashboardService = buyerDashboardService;
    }

    /**
     * Buyer Dashboard
     */
    @GetMapping("/{buyerId}")
    public ResponseEntity<BuyerDashboardDTO> getDashboard(
            @PathVariable Long buyerId) {

        BuyerDashboardDTO dashboard =
                buyerDashboardService.getDashboard(buyerId);

        return ResponseEntity.ok(dashboard);
    }

}