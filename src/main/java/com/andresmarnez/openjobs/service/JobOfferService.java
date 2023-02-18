package com.andresmarnez.openjobs.service;

import com.andresmarnez.openjobs.entities.Company;
import com.andresmarnez.openjobs.entities.JobOffer;
import com.andresmarnez.openjobs.repositories.CompanyRepository;
import com.andresmarnez.openjobs.repositories.JobOfferRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JobOfferService {

	private final JobOfferRepository jobOfferRepository;
	private final CompanyRepository companyRepository;

	public JobOfferService(JobOfferRepository jobOfferRepository, CompanyRepository companyRepository) {
		this.jobOfferRepository = jobOfferRepository;
		this.companyRepository = companyRepository;
	}

	public List<JobOffer> findAll() {
		return jobOfferRepository.findAll();
	}

	public List<JobOffer> findActiveOffers() {
		return jobOfferRepository.findAllByIsActiveTrue();
	}

	public List<JobOffer> findPaginatedOffers(int page, int size, String sortBy, Optional<Boolean> des) {

		Pageable pageable;
		String sortStr;

		if (sortBy == null) sortBy = "";

		switch (sortBy) {
			case "title" -> sortStr = "jobTitle";
			case "descrp" -> sortStr = "jobDescription";
			case "published" -> sortStr = "publishedTime";
			case "location" -> sortStr = "location";
			case "vacancies" -> sortStr = "vacancies";
			case "company" -> sortStr = "company";
			default -> {
				pageable = PageRequest.of(page, size);
				return jobOfferRepository.findAllByIsActiveTrue(pageable);
			}
		}
		Sort sort = Sort.by(
				(des.isPresent() && des.get()) ? Sort.Direction.DESC : Sort.Direction.ASC,
				sortStr);


		pageable = PageRequest.of(page, size, sort);
		return jobOfferRepository.findAllByIsActiveTrue(pageable);
	}

	public boolean addOffer(long company_id, JobOffer offer){
		Optional<Company> company = companyRepository.findById(company_id);
		if (offer == null || company.isEmpty()) return false;

		offer.setCompany(company.get());
		jobOfferRepository.save(offer);
		return true;
	}

	public boolean updateOffer(long company_id, JobOffer offer){
		Optional<Company> company = companyRepository.findById(company_id);
		if (offer == null || company.isEmpty()) return false;

		offer.setCompany(company.get());
		jobOfferRepository.save(offer);
		return true;
	}

	public JobOffer findById(long id){
		Optional<JobOffer> jobOffer = jobOfferRepository.findById(id);
		return jobOffer.orElse(null);
	}

	public String getJsonFile() {

		List<JobOffer> jobOffers = jobOfferRepository.findAllByIsActiveTrue();

		JSONObject obj = new JSONObject();
		JSONArray offerArray = new JSONArray();

		List<JobOffer> offers = jobOfferRepository.findAllByIsActiveTrue();

		for (JobOffer offer : offers) {
			JSONObject offerJSON = new JSONObject();
			offerJSON.put("vacancies", offer.getVacancies());
			offerJSON.put("location", offer.getLocation());
			offerJSON.put("jobDescription", offer.getJobDescription());
			offerJSON.put("publishedTime", offer.getPublishedTime());
			offerJSON.put("company_name", offer.getCompany().getName());
			offerJSON.put("jobTitle", offer.getJobTitle());
			offerJSON.put("id", offer.getId());

			JSONArray tags = new JSONArray();
			offer.getTags().forEach(tag -> tags.put(tag.getCategory()));

			// Won't be sending candidates' info for sec reasons.
			//JSONArray candidates = new JSONArray();

			offerArray.put(offerJSON);
		}

		obj.put("offers", offerArray);

		System.out.println(obj.toString(4));

		//Name will cointain date
		String fileName = LocalDate.now()+ "_offers.txt";
		try(FileWriter fileWriter = new FileWriter(fileName)){

			fileWriter.write(obj.toString());
			fileName = Paths.get(fileName).toRealPath().toString();

		} catch (IOException e){
			return "NOT CREATED";
		}
		return fileName;
	}
}
