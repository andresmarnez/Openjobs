package com.andresmarnez.openjobs.controller;

import com.andresmarnez.openjobs.entities.JobOffer;
import com.andresmarnez.openjobs.service.JobOfferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * This class is the main class for the JSON API.
 */
@RestController
@RequestMapping("/")
public class OpenJobsController {

	private final JobOfferService jobOfferService;

	public OpenJobsController(JobOfferService jobOfferService) {
		this.jobOfferService = jobOfferService;
	}

	@GetMapping("/offers")
	public List<JobOffer> getAllOffers(){
		return jobOfferService.findActiveOffers();
	}
}
