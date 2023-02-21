package com.andresmarnez.openjobs.controller;

import com.andresmarnez.openjobs.entities.JobOffer;
import com.andresmarnez.openjobs.service.JobOfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web")
public class WebController {

	private final JobOfferService jobOfferService;

	public WebController(JobOfferService jobOfferService) {
		this.jobOfferService = jobOfferService;
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

	@PostMapping("/edit/")
	public String postEditedOffer(@ModelAttribute("offer") JobOffer offer){
		jobOfferService.updateOffer(offer.getId(),offer);
		return "redirect:/web/";
	}

	@PostMapping("/add/{id}")
	public String createOffer(@PathVariable("id") Long id, @ModelAttribute("offer") JobOffer offer){
		jobOfferService.addOffer(id,offer);
		return "redirect:/web/";
	}

	@PostMapping("/remove/")
	public String removeOffer(@ModelAttribute("id") Long id){
		jobOfferService.removeById(id);
		return "redirect:/web/";
	}

}
