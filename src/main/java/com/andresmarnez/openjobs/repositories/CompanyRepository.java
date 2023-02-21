package com.andresmarnez.openjobs.repositories;

import com.andresmarnez.openjobs.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Company save(Company company);

	@Query (value = "select distinct c.COUNTRY from COMPANIES c", nativeQuery = true)
	Set<String> getCountries();

	@Query(value = "select count(*) from COMPANIES c WHERE c.COUNTRY like ?1", nativeQuery = true)
	long getCompaniesInCountry(String country);

	boolean existsById(Long id);

	void deleteById(Long id);
}