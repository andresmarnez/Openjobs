package com.andresmarnez.openjobs.controller;

import com.andresmarnez.openjobs.entities.Company;
import com.andresmarnez.openjobs.service.CompanyService;
import com.andresmarnez.openjobs.service.JobOfferService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * This class is the main class for the JSON API.
 */
@RestController
@RequestMapping("/")
public class OpenJobsController {

	private final JobOfferService jobOfferService;
	private final CompanyService companyService;

	public OpenJobsController(JobOfferService jobOfferService, CompanyService companyService) {
		this.jobOfferService = jobOfferService;
		this.companyService = companyService;
	}

	@Operation(summary = "Get a list of the companies.")
	@GetMapping("/companies")
	public List<Company> getAllCompanies(){
		return companyService.getAllCompanies();
	}


}
