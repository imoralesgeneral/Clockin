package com.uoc.clockin.app.companies.models.service;

import java.util.List;

import com.uoc.clockin.app.commons.models.entity.Company;

public interface ICompanyService {

	public List<Company> findAll();

	public Company findById(Long id);
	
	public Company findByName(String name);
	
	public Company save(Company company);
	
	public void deleteById(Long id);
	
}
