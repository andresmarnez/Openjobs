package com.andresmarnez.openjobs.repositories;

import com.andresmarnez.openjobs.entities.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

}