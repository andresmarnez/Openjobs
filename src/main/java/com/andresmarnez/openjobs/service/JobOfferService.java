package com.andresmarnez.openjobs.service;

import com.andresmarnez.openjobs.entities.Company;
import com.andresmarnez.openjobs.entities.JobOffer;
import com.andresmarnez.openjobs.repositories.CompanyRepository;
import com.andresmarnez.openjobs.repositories.JobOfferRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobOfferService {

	private final JobOfferRepository jobOfferRepository;
	private final CompanyRepository companyRepository;

	public JobOfferService(JobOfferRepository jobOfferRepository, CompanyRepository companyRepository) {
		this.jobOfferRepository = jobOfferRepository;
		this.companyRepository = companyRepository;
	}

	public List<JobOffer> findAll() {
		return jobOfferRepository.findAll();
	}

	public List<JobOffer> findActiveOffers() {
		return jobOfferRepository.findAllByIsActive(true);
	}

	public List<JobOffer> findPaginatedOffers(int page, int size, String sortBy, Optional<Boolean> des) {

		Pageable pageable;
		String sortStr = "";

		if (sortBy == null) sortBy = "";

		switch (sortBy) {
			case "title" -> sortStr = "jobTitle";
			case "descrp" -> sortStr = "jobDescription";
			case "published" -> sortStr = "publishedTime";
			case "location" -> sortStr = "location";
			case "vacancies" -> sortStr = "vacancies";
			case "company" -> sortStr = "company";
			default -> {
				pageable = PageRequest.of(page, size);
				return jobOfferRepository.findAllByIsActive(true, pageable);
			}
		}
		Sort sort = Sort.by(
				(des.isPresent() && des.get()) ? Sort.Direction.DESC : Sort.Direction.ASC,
				sortStr);


		pageable = PageRequest.of(page, size, sort);
		return jobOfferRepository.findAllByIsActive(true, pageable);
	}

	public boolean addOffer(long company_id, JobOffer offer){
		Optional<Company> company = companyRepository.findById(company_id);
		if (offer == null || company.isEmpty()) return false;

		offer.setCompany(company.get());
		return  (jobOfferRepository.save(offer) != null);
	}
}
