package com.uoc.clockin.app.companies.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uoc.clockin.app.companies.models.dao.CompanyDao;
import com.uoc.clockin.app.commons.models.entity.Company;

@Service
public class CompanyServiceImpl implements ICompanyService{

	@Autowired
	private CompanyDao companyDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Company> findAll() {
		return (List<Company>) companyDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Company findById(Long id) {
		return companyDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Company findByName(String name) {
		return companyDao.findByName(name);
	}

	@Override
	@Transactional
	public Company save(Company company) {
		return companyDao.save(company);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		companyDao.deleteById(id);
	}

}
