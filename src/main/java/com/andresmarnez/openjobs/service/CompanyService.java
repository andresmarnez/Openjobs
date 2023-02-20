package com.andresmarnez.openjobs.service;

import com.andresmarnez.openjobs.entities.Company;
import com.andresmarnez.openjobs.entities.JobOffer;
import com.andresmarnez.openjobs.repositories.CompanyRepository;
import com.andresmarnez.openjobs.repositories.JobOfferRepository;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

	private final CompanyRepository companyRepository;
	private final JobOfferRepository jobOfferRepository;

	public CompanyService(CompanyRepository companyRepository, JobOfferRepository jobOfferRepository) {
		this.companyRepository = companyRepository;
		this.jobOfferRepository = jobOfferRepository;
	}

	public Company getCompany(Long id) {
		Optional<Company> company;
		return ( (company = companyRepository.findById(id)).isPresent() ) ? company.get() : null;
	}

	public List<Company> getAllCompanies(){
		return companyRepository.findAll();
	}

	public boolean removeCompanyById(long id){

		if (companyRepository.existsById(id)) {
			companyRepository.deleteById(id);
			return true;
		}
		else return false;
	}

	public JobOffer getNthOffer(Long company_id, Long num) {
		List<JobOffer> list = jobOfferRepository.findByCompanyIdOrderByPublishedTime(company_id);
		return list.get(num.intValue() - 1);
	}

	public List<JobOffer> getAllOffersFrom(Long company_id) {
		return jobOfferRepository.findByCompanyId(company_id);
	}

}
