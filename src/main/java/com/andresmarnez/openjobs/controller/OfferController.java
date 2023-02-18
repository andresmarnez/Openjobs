package com.andresmarnez.openjobs.controller;

import com.andresmarnez.openjobs.entities.JobOffer;
import com.andresmarnez.openjobs.service.JobOfferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/offers")
public class OfferController {

	private final JobOfferService jobOfferService;

	public OfferController(JobOfferService jobOfferService) {
		this.jobOfferService = jobOfferService;
	}

	@Operation(summary = "Get a list of the jop offers.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the offers.",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = JobOffer.class)) }),
			@ApiResponse(responseCode = "400", description = "Error",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Offers not found",
					content = @Content) })
	@GetMapping("")
	public List<JobOffer> getAllOffers(){
		return jobOfferService.findAll();
	}

	@Operation(summary = "Gets the an specific job offer.")
	@GetMapping("/{id}")
	public JobOffer getJobOffer(@PathVariable("id") long id){return jobOfferService.findById(id);}

	@Operation(summary = "Search within all active offers.")
	@GetMapping("/active")
	public List<JobOffer> getActiveOffers(){return jobOfferService.findActiveOffers();}

	@Operation(summary = "Stores all active joboffers in a fila and returns the path.")
	@GetMapping("/active/file-json")
	public String getJsonFile(){
		return jobOfferService.getJsonFile();
	}

	//@Request(defaultValue="ASC", required = false) Sort.Directon direction
	@Operation(summary = "Filtering the offers by pagination.")
	@GetMapping("/filter")
	public List<JobOffer> getOffersPaged(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortBy, @RequestParam Optional<Boolean> des){

		if (page == null || size == null){
			return null;
		} else {
			return jobOfferService.findPaginatedOffers(page,size,sortBy, des);
		}
	}

	@Operation(summary = "Adds a new offer to a company")
	@PostMapping("/add/{id}")
	public ResponseEntity<String> addOffer(@PathVariable(value = "id") Long id, @RequestBody JobOffer offer){
		return (jobOfferService.addOffer(id, offer)) ? ResponseEntity.status(HttpStatus.OK).body("JobOffer added.") :
				ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("JobOffer NOT added.");
	}

	@Operation(summary = "Adds a new offer to a company")
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateOffer(@PathVariable("id") Long id, @RequestBody JobOffer offer) {

		return (jobOfferService.updateOffer(id, offer)) ? ResponseEntity.status(HttpStatus.OK).body("JobOffer updated.") :
				ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("JobOffer NOT updated.");
	}
}
