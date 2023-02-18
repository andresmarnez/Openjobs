package com.andresmarnez.openjobs.repositories;

import com.andresmarnez.openjobs.entities.Company;
import com.andresmarnez.openjobs.entities.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Company save(Company company);

	boolean existsById(Long id);

	void deleteById(Long id);
}