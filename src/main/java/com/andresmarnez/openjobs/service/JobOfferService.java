package com.andresmarnez.openjobs.service;

import com.andresmarnez.openjobs.entities.JobOffer;
import com.andresmarnez.openjobs.repositories.JobOfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobOfferService {

	private final JobOfferRepository jobOfferRepository;

	public JobOfferService(JobOfferRepository jobOfferRepository) {
		this.jobOfferRepository = jobOfferRepository;
	}

	public List<JobOffer> findAll(){
		return jobOfferRepository.findAll();
	}

	public List<JobOffer> findActiveOffers(){return jobOfferRepository.findAllByIsActive(true);}


}
