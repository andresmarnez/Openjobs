package com.andresmarnez.openjobs.repositories;

import com.andresmarnez.openjobs.entities.JobOffer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

	List<JobOffer> findAllByIsActiveTrue();

	List<JobOffer> findAllByIsActiveFalse();

	List<JobOffer> findAllByIsActiveTrue(Pageable pageable);

	List<JobOffer> findAllByIsActiveFalse(Pageable pageable);

	List<JobOffer> findAll();

	boolean existsById(Long id);
}