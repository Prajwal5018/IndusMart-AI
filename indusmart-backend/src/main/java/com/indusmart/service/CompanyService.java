package com.indusmart.service;

import com.indusmart.dto.CompanyRequest;
import com.indusmart.entity.Company;
import com.indusmart.entity.User;
import com.indusmart.repository.CompanyRepository;
import com.indusmart.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public CompanyService(CompanyRepository companyRepository,
                          UserRepository userRepository) {

        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    /**
     * Register Company
     */
    public String registerCompany(CompanyRequest request) {

        if (companyRepository.existsByGstNumber(request.getGstNumber())) {
            return "GST already registered";
        }

        if (companyRepository.existsByPanNumber(request.getPanNumber())) {
            return "PAN already registered";
        }

        Optional<User> optionalUser =
                userRepository.findById(request.getOwnerId());

        if (optionalUser.isEmpty()) {
            return "Seller not found";
        }

        User owner = optionalUser.get();

        Company company = Company.builder()
                .companyName(request.getCompanyName())
                .gstNumber(request.getGstNumber())
                .panNumber(request.getPanNumber())
                .email(request.getEmail())
                .phone(request.getPhone())
                .website(request.getWebsite())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .pincode(request.getPincode())
                .description(request.getDescription())
                .logoUrl(request.getLogoUrl())
                .verified(false)
                .owner(owner)
                .build();

        companyRepository.save(company);

        return "Company Registered Successfully";
    }

    /**
     * Get Company By Id
     */
    public Company getCompany(Long companyId) {

        return companyRepository.findById(companyId)
                .orElse(null);

    }

    /**
     * Update Company
     */
    public String updateCompany(Long companyId,
                                CompanyRequest request) {

        Optional<Company> optionalCompany =
                companyRepository.findById(companyId);

        if (optionalCompany.isEmpty()) {
            return "Company not found";
        }

        Optional<User> optionalUser =
                userRepository.findById(request.getOwnerId());

        if (optionalUser.isEmpty()) {
            return "Seller not found";
        }

        Company company = optionalCompany.get();

        company.setCompanyName(request.getCompanyName());
        company.setGstNumber(request.getGstNumber());
        company.setPanNumber(request.getPanNumber());
        company.setEmail(request.getEmail());
        company.setPhone(request.getPhone());
        company.setWebsite(request.getWebsite());
        company.setAddress(request.getAddress());
        company.setCity(request.getCity());
        company.setState(request.getState());
        company.setCountry(request.getCountry());
        company.setPincode(request.getPincode());
        company.setDescription(request.getDescription());
        company.setLogoUrl(request.getLogoUrl());
        company.setOwner(optionalUser.get());

        companyRepository.save(company);

        return "Company Updated Successfully";
    }

    /**
     * Delete Company
     */
    public String deleteCompany(Long companyId) {

        Optional<Company> optionalCompany =
                companyRepository.findById(companyId);

        if (optionalCompany.isEmpty()) {
            return "Company not found";
        }

        companyRepository.delete(optionalCompany.get());

        return "Company Deleted Successfully";
    }

}