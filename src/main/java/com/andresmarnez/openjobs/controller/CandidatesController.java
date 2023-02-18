package com.andresmarnez.openjobs.controller;

import com.andresmarnez.openjobs.entities.Candidate;
import com.andresmarnez.openjobs.service.CandidateService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/candidates")
public class CandidatesController {

	private final CandidateService candidateService;

	public CandidatesController(CandidateService candidateService) {
		this.candidateService = candidateService;
	}

	@Operation(summary = "Returns all candidates.")
	@GetMapping("/")
	public List<Candidate> getAllCandidates(){
		return candidateService.getAllCandidates();
	}

	@Operation(summary = "Returns all candidates open to work.")
	@GetMapping("/open-for-work")
	public List<Candidate> getAllCandidatesOpen() {
		return candidateService.getAllCandidatesOpenTrue();
	}

	@Operation(summary = "Returns all candidates intereted in a category.")
	@GetMapping("/interest")
	public List<Candidate> getCandidatesInterested(@RequestParam("category") String category){
		return  candidateService.getAllCandidatesInterested(category);
	}



}
