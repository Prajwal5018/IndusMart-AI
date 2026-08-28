package com.indusmart.controller;

import com.indusmart.dto.CompanyRequest;
import com.indusmart.entity.Company;
import com.indusmart.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
@CrossOrigin(origins = "*")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * Register Company
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerCompany(
            @RequestBody CompanyRequest request) {

        return ResponseEntity.ok(
                companyService.registerCompany(request)
        );
    }

    /**
     * Get Company
     */
    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompany(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(
                companyService.getCompany(companyId)
        );
    }

    /**
     * Update Company
     */
    @PutMapping("/update/{companyId}")
    public ResponseEntity<String> updateCompany(
            @PathVariable Long companyId,
            @RequestBody CompanyRequest request) {

        return ResponseEntity.ok(
                companyService.updateCompany(companyId, request)
        );
    }

    /**
     * Delete Company
     */
    @DeleteMapping("/delete/{companyId}")
    public ResponseEntity<String> deleteCompany(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(
                companyService.deleteCompany(companyId)
        );
    }

}