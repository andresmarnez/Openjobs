package com.andresmarnez.openjobs.service;

import com.andresmarnez.openjobs.entities.Company;
import com.andresmarnez.openjobs.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

	private final CompanyRepository companyRepository;

	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	public List<Company> getAllCompanies(){
		return companyRepository.findAll();
	}

	public boolean removeCompanyById(long id){

		if (companyRepository.existsById(id)) {
			companyRepository.deleteById(id);
			return true;
		}
		else return false;
	}

}
