package com.uoc.clockin.app.users.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uoc.clockin.app.commons.models.entity.DailyEntry;

@FeignClient(name = "service-dailyentries")
public interface EntriesFeignClient {
	
	@GetMapping("/api/entries/epoch/{idUser}/{epochStart}/{epochEnd}")
	public List<DailyEntry> findBetweenEpochsUser(@RequestParam long epochStart, @RequestParam long epochEnd, @RequestParam int idUser);

}
