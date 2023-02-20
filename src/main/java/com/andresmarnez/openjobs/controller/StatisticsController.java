package com.andresmarnez.openjobs.controller;

import com.andresmarnez.openjobs.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/stats")
public class StatisticsController {

	private final StatisticsService service;

	public StatisticsController(StatisticsService service) {
		this.service = service;
	}

	@Operation(summary = "Returns the number of companies per country.")
	@GetMapping("/country")
	public Map<String, Long> summaryCountries(){
		return service.getSummaryCountries();
	}
}
