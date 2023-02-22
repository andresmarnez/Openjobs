package com.andresmarnez.openjobs.controller;

import com.andresmarnez.openjobs.entities.JobOffer;
import com.andresmarnez.openjobs.service.CompanyService;
import com.andresmarnez.openjobs.service.JobOfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web")
public class WebController {

	private final JobOfferService jobOfferService;
	private final CompanyService companyService;

	public WebController(JobOfferService jobOfferService, CompanyService companyService) {
		this.jobOfferService = jobOfferService;
		this.companyService = companyService;
	}

	@GetMapping("/")
	public String getIndex(Model model) {
		model.addAttribute("offers", jobOfferService.findActiveOffers());
		model.addAttribute("search", null);
		return "offers/index.html";
	}

	@GetMapping("/search")
	public String getIndex(Model model, @RequestParam("search") String name) {
		model.addAttribute("offers", jobOfferService.searchByTitle(name));
		if (!name.isBlank())
		model.addAttribute("search", "Results for: \"" + name + "\"");
		return "offers/index.html";
	}

	@GetMapping("/edit/{id}")
	public String getOfferEdition(Model model, @PathVariable("id") Long id){
		model.addAttribute("offer",jobOfferService.findById(id));
		return "offers/update.html";
	}

	@GetMapping("/new")
	public String getCreationFrom(Model model){
		JobOffer offer =  new JobOffer();
		offer.setCompany(companyService.getCompany(1L));
		model.addAttribute("offer",offer);
		return "offers/create.html";
	}

	@PostMapping("/edit/")
	public String postEditedOffer(@ModelAttribute("offer") JobOffer offer){
		jobOfferService.updateOffer(offer.getId(),offer);
		return "redirect:/web/";
	}

	@PostMapping("/new/")
	public String createOffer(@ModelAttribute("offer") JobOffer offer){
		offer.setCompany(companyService.getCompany(1L));
		jobOfferService.addOffer(offer);
		return "redirect:/web/";
	}

	@PostMapping("/remove/")
	public String removeOffer(@ModelAttribute("id") Long id){
		jobOfferService.removeById(id);
		return "redirect:/web/";
	}

}
