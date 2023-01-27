package com.andresmarnez.openjobs.repositories;

import com.andresmarnez.openjobs.entities.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

	public List<JobOffer> findAllByIsActive(boolean isActive);
}