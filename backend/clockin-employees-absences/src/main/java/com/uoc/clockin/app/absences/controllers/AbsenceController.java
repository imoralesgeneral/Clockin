package com.uoc.clockin.app.absences.controllers;

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

import com.uoc.clockin.app.commons.models.entity.Absence;
import com.uoc.clockin.app.commons.models.entity.Message;
import com.uoc.clockin.app.commons.models.entity.TypeAbsence;
import com.uoc.clockin.app.absences.models.service.IAbsenceService;

@RefreshScope
@RestController
public class AbsenceController {

	@Autowired
	private IAbsenceService absenceService;
	
	@GetMapping("/api/absences/")
	public List<Absence> getAbsences() {
		return absenceService.findAll();
	}
	
	@GetMapping("/api/absences/coduser/{codUser}")
	public List<Absence> getAbsencesByCodUser(@PathVariable int codUser) {
		return absenceService.findByCodUser(codUser);
	}
	
	@GetMapping("/api/absences/type/{type}") 
	public List<Absence> getAbsencesByType(@PathVariable TypeAbsence type) { 
		return absenceService.findByType(type.ordinal());
	}
	
	@GetMapping("/api/absences/notvalidated/company/{idCompany}")
	public List<Absence> getAbsencesNotValidatedCompany(@PathVariable int idCompany) {
		return absenceService.findNotValidatedCompany(idCompany);
	}
	
	@GetMapping("/api/absences/notvalidated/coduser/{coduser}") 
	public List<Absence> getAbsencesNotValidatedUser(@PathVariable int coduser) {
		return absenceService.findNotValidatedUser(coduser);
	}
	
	@GetMapping("/api/absences/spent/{coduser}/{epochStart}") 
	public List<Absence> getSpentHolidays(@PathVariable int coduser, @PathVariable long epochStart) {
		return absenceService.findSpentHolidays(coduser, epochStart);
	}
	
	@GetMapping("/api/absences/id/{id}") 
	public Absence getAbsence(@PathVariable Long id) {
		return absenceService.findById(id);
	}
	
	@GetMapping("/api/absences/message/{id_user}/{id_company}") 
	public Message getMessage(@PathVariable int id_user, @PathVariable int id_company) {
		return absenceService.findMessageUser(id_user, id_company);
	}
	
	@GetMapping("/api/absences/rest/{codUser}")
	public Long getRestHolidays(@PathVariable int codUser) {
		return absenceService.findRestHolidays(codUser);
	}
	
	@PostMapping("/api/absences/create/")
	@ResponseStatus(HttpStatus.CREATED)
	public Absence create(@RequestBody Absence absence) {
		return absenceService.save(absence);
	}
	
	@PutMapping("/api/absences/update/{id}/{result}")
	@ResponseStatus(HttpStatus.CREATED)
	public Absence update(@PathVariable Long id, @PathVariable Boolean result) {
		Absence absence = absenceService.findById(id);
		absence.setValidated(result);
		return absenceService.save(absence);
	}
	
	@DeleteMapping("/api/absences/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		absenceService.deleteById(id);
	}
	
}
