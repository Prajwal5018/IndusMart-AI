package com.indusmart.controller;

import com.indusmart.dto.AdminDashboardDTO;
import com.indusmart.service.AdminDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/dashboard")
@CrossOrigin(origins = "*")
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    public AdminDashboardController(
            AdminDashboardService adminDashboardService) {

        this.adminDashboardService = adminDashboardService;
    }

    /**
     * Get Complete Admin Dashboard
     */
    @GetMapping
    public ResponseEntity<AdminDashboardDTO> getDashboard() {

        AdminDashboardDTO dashboard =
                adminDashboardService.getDashboard();

        return ResponseEntity.ok(dashboard);
    }

}