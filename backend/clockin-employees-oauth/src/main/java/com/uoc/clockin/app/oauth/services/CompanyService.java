package com.uoc.clockin.app.oauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uoc.clockin.app.commons.models.entity.Company;
import com.uoc.clockin.app.oauth.clients.CompanyFeignClient;

@Service
public class CompanyService implements ICompanyService {
	
	@Autowired
	private CompanyFeignClient client;

	@Override
	public Company findById(Long id) {
		return client.findById(id);
	}

}
