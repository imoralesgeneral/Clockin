package com.uoc.clockin.app.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uoc.clockin.app.commons.models.entity.Company;

@FeignClient(name = "service-companies")
public interface CompanyFeignClient {

	@GetMapping("/api/companies/id/{id}")
	public Company findById(@RequestParam Long id);
	
}
