package com.andresmarnez.openjobs.service;

import com.andresmarnez.openjobs.repositories.CompanyRepository;
import com.andresmarnez.openjobs.repositories.JobOfferRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class StatisticsService {

	private final JobOfferRepository jobOfferRepository;
	private final CompanyRepository companyRepository;


	public StatisticsService(JobOfferRepository jobOfferRepository, CompanyRepository companyRepository) {
		this.jobOfferRepository = jobOfferRepository;
		this.companyRepository = companyRepository;
	}


	public Map<String, Long> getSummaryCountries() {
		Map<String, Long> map = new HashMap<>();
		Set<String> compCountries = companyRepository.getCountries();

		compCountries.forEach( c -> map.put(c, companyRepository.getCompaniesInCountry(c)));

		return map;
	}
}
