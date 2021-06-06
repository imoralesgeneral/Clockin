package com.uoc.clockin.app.companies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uoc.clockin.app.commons.models.entity.Company;
import com.uoc.clockin.app.companies.models.service.ICompanyService;

@RefreshScope
@RestController
public class CompanyController {
	
	@Autowired
	ICompanyService companyService;
	
	@GetMapping("/api/companies/")
	public List<Company> getCompanies() {
		return companyService.findAll();
	}

	@GetMapping("/api/companies/id/{id}")
	public Company getCompany(@PathVariable Long id) {
		return companyService.findById(id);
	}
	
	@GetMapping("/api/companies/name/{name}")
	public Company getCompanyByName(@PathVariable String name) {
		return companyService.findByName(name);
	}
	
	@PostMapping("/api/companies/create/")
	@ResponseStatus(HttpStatus.CREATED)
	public Company create(@RequestBody Company company) {
		if(company.getName() == null) return null;
		return companyService.save(company);
	}
	
	@PutMapping("/companies/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Company update(@RequestBody Company company, @PathVariable Long id) {
		Company companyDb = companyService.findById(id);
		// TODO crear metodo para actualizar campos
		return companyService.save(companyDb);
	}
	
	@DeleteMapping("/companies/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		companyService.deleteById(id);
	}

}
