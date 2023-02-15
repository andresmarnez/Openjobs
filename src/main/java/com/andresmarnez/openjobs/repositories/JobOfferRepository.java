package com.andresmarnez.openjobs.repositories;

import com.andresmarnez.openjobs.entities.JobOffer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

	List<JobOffer> findAllByIsActive(boolean isActive);

	List<JobOffer> findAllByIsActive(boolean isActive, Pageable pageable);

	List<JobOffer> findAllByIsActive(boolean isActive, Sort sort);

	JobOffer save(JobOffer offer);

	boolean existsById(Long id);
}