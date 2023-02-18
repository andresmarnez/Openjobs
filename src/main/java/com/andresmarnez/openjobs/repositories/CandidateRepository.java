package com.andresmarnez.openjobs.repositories;

import com.andresmarnez.openjobs.entities.Candidate;
import com.andresmarnez.openjobs.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

	List<Candidate> findAll();

	List<Candidate> findAllByCategoriesContains(Category category);

	List<Candidate> findAllByIsOpenToWorkTrue();
}