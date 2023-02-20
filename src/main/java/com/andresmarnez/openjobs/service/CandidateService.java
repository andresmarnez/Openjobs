package com.andresmarnez.openjobs.service;

import com.andresmarnez.openjobs.entities.Candidate;
import com.andresmarnez.openjobs.entities.Category;
import com.andresmarnez.openjobs.repositories.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

	public final CandidateRepository candidateRepository;

	public CandidateService(CandidateRepository candidateRepository) {
		this.candidateRepository = candidateRepository;
	}

	public Candidate getCandidateById(Long id) {
		Optional<Candidate> candidate;
		return ((candidate = candidateRepository.findById(id)).isPresent() ) ? candidate.get(): null; }

	public List<Candidate> getAllCandidates(){
		return candidateRepository.findAll();
	}

	public List<Candidate> getAllCandidatesOpenTrue() {
		return candidateRepository.findAllByIsOpenToWorkTrue();
	}

	public List<Candidate> getAllCandidatesInterested(String category) {
		return candidateRepository.findAllByCategoriesContains(new Category(category));
	}
}
