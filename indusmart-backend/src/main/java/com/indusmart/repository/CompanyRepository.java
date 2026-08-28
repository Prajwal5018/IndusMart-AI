package com.indusmart.repository;

import com.indusmart.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByGstNumber(String gstNumber);

    boolean existsByPanNumber(String panNumber);

}