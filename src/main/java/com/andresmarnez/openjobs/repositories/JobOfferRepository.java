package com.andresmarnez.openjobs.repositories;

import com.andresmarnez.openjobs.entities.JobOffer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

	List<JobOffer> findAllByIsActiveTrue();

	List<JobOffer> findAllByIsActiveFalse();

	List<JobOffer> findAllByIsActiveTrue(Pageable pageable);

	List<JobOffer> findAllByIsActiveFalse(Pageable pageable);

	List<JobOffer> findAll();

	@Transactional
	@Modifying
	@Query(value = "update job_offers set job_description=?1, job_title=?2, location=?3 where id=?4", nativeQuery = true)
	Integer update(String jobDesc, String title, String location, Long id);

	boolean existsById(Long id);

	List<JobOffer> findByIsActiveTrueAndJobTitleContainingIgnoreCase(String text);

	List<JobOffer> findByCompanyIdOrderByPublishedTime(Long company_id);

	List<JobOffer> findByCompanyId(Long company_id);
}