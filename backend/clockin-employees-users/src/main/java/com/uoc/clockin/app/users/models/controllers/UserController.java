package com.uoc.clockin.app.users.models.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uoc.clockin.app.commons.models.entity.Absence;
import com.uoc.clockin.app.commons.models.entity.DailyEntry;
import com.uoc.clockin.app.commons.models.entity.PaySlip;
import com.uoc.clockin.app.commons.models.entity.User;
import com.uoc.clockin.app.users.clients.AbsencesFeignClient;
import com.uoc.clockin.app.users.clients.EntriesFeignClient;
import com.uoc.clockin.app.users.models.service.IUserService;

@RefreshScope
@RestController
public class UserController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private EntriesFeignClient clientEntries;
	@Autowired
	private AbsencesFeignClient clientAbsences;
	
	@GetMapping("/api/users/")
	public List<User> getUsers() {
		return userService.findAll();
	}
	
	@GetMapping("/api/users/id/{id}")
	public User getUser(@PathVariable Long id) {
		return userService.findById(id);
	}
	
	@GetMapping("/api/users/username/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}
	
	@GetMapping("/api/users/email/{email}")
	public User getUserByEmail(@PathVariable String email) {
		return userService.findByEmail(email);
	}
	
	@GetMapping("/api/users/find/{value}")
	public User getUserByUsernameOrEmail(@PathVariable String value) {
		return userService.findByUsernameOrEmail(value, value);
	}
	
	@GetMapping("/api/users/idcompany/{idCompany}") 
	public List<User> getUsersByIdCompany(@PathVariable int idCompany) {
		return userService.findByIdCompany(idCompany);
	}
	/////
	@GetMapping("/api/users/validated/{validated}") 
	public List<User> getUsersByValidated(@PathVariable boolean validated) {
		return userService.findByValidated(validated);
	}
	
	@GetMapping("/api/users/term/{idCompany}/{term}") 
	public List<User> getUsersByTerm(@PathVariable int idCompany, @PathVariable String term) {
		return userService.findByTerm(idCompany, term);
	}
	/////
	@GetMapping("/api/users/idcompany/{idCompany}/validated/{validated}") 
	public List<User> getUsersByIdCompanyAndValidated(@PathVariable int idCompany, @PathVariable boolean validated) {
		return userService.findByIdCompanyAndValidated(idCompany, validated);
	}
	
	@GetMapping("/api/users/payslip/{idUser}/{month}/{year}") 
	public PaySlip getPaySlip(@PathVariable int idUser, @PathVariable int month, @PathVariable int year) {
		User user = userService.findById((long)idUser);
		LocalDate ldStart = LocalDate.of(year, month, 1);
		LocalDate ldEnd = LocalDate.of(year, month+1, 1);
		long epochStart = ldStart.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
		long epochEnd = ldEnd.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
		List<DailyEntry> entriesMonth = clientEntries.findBetweenEpochsUser(epochStart, epochEnd, idUser); 
		List<Absence> absencesMonth = clientAbsences.findSpentHolidays(idUser, epochStart); 
		System.out.println("start: "+epochStart+"-"+ldStart+" || end: "+epochEnd+"-"+ldEnd+" || "+absencesMonth);
		return new PaySlip(user, entriesMonth, absencesMonth, epochStart, epochEnd); 
	}
	
	@PostMapping("/api/users/create/")
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String pass = user.getPassword();
		user.setPassword(passwordEncoder.encode(pass));
		return userService.save(user);
	}
	
	@PutMapping("/api/users/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public User update(@RequestBody User user, @PathVariable Long id) {
		User userR = userService.findById(id);
		String pass = userR.getPassword();
		userService.deleteById(id);
		user.setPassword(pass);
		return userService.save(user);
	}
	
	@DeleteMapping("/api/users/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) { 
		userService.deleteById(id);
	}

}
