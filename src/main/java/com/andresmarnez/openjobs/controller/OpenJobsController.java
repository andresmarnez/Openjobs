package com.andresmarnez.openjobs.controller;

import com.andresmarnez.openjobs.entities.Company;
import com.andresmarnez.openjobs.entities.JobOffer;
import com.andresmarnez.openjobs.service.CompanyService;
import com.andresmarnez.openjobs.service.JobOfferService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is the main class for the JSON API.
 */

@CrossOrigin
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

	@Operation(summary = "Returns company's information.")
	@GetMapping("/company/{company_id}")
	public Company getCompany(@PathVariable("company_id") Long id){
		return companyService.getCompany(id);
	}

	@Operation(summary = "Returns all the offers of a company.")
	@GetMapping("/company/{company_id}/offers")
	public List<JobOffer> getAllOffersFromCompany(@PathVariable("company_id") Long company_id){
		return companyService.getAllOffersFrom(company_id);
	}

	@Operation(summary = "Returns the nth offer of a company starting by 1 and ordered by publishing date.")
	@GetMapping("/company/{company_id}/offers/{num}")
	public JobOffer getNthOfferFromCompany(@PathVariable("company_id") Long company_id, @PathVariable("num") Long num){
		return companyService.getNthOffer(company_id, num);
	}

	@Operation(summary = "Removes a company based on the id provided this will also delete all Offers that Companty has.")
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeCompany(@PathVariable("id") Long id){
		return (companyService.removeCompanyById(id)) ? ResponseEntity.status(HttpStatus.OK).body("Company " + id + " removed.") :
				ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Company NOT removed.");
	}

}
