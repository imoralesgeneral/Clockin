package com.uoc.clockin.app.users.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uoc.clockin.app.commons.models.entity.Absence;

@FeignClient(name = "service-absences")
public interface AbsencesFeignClient {
	
	@GetMapping("/api/absences/spent/{coduser}/{epochStart}")
	public List<Absence> findSpentHolidays(@RequestParam int coduser, @RequestParam long epochStart);

}
