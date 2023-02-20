package com.andresmarnez.openjobs.repositories;

import com.andresmarnez.openjobs.entities.JobOffer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

	List<JobOffer> findAllByIsActiveTrue();

	List<JobOffer> findAllByIsActiveFalse();

	List<JobOffer> findAllByIsActiveTrue(Pageable pageable);

	List<JobOffer> findAllByIsActiveFalse(Pageable pageable);

	List<JobOffer> findAll();

	boolean existsById(Long id);

	List<JobOffer> findByIsActiveTrueAndJobTitleContainingIgnoreCase(String text);

	List<JobOffer> findByCompanyIdOrderByPublishedTime(Long company_id);

	List<JobOffer> findByCompanyId(Long company_id);
}