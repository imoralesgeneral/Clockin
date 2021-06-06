package com.uoc.clockin.app.companies.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uoc.clockin.app.commons.models.entity.Company;

public interface CompanyDao extends CrudRepository<Company, Long>{

	@Query(value = "SELECT * FROM COMPANY WHERE NAME = ?1", nativeQuery = true)
	Company findByName(String name); 
	
}
