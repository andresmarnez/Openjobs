package com.andresmarnez.openjobs.repositories;

import com.andresmarnez.openjobs.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}