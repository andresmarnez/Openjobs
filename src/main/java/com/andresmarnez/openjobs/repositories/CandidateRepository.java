package com.andresmarnez.openjobs.repositories;

import com.andresmarnez.openjobs.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}