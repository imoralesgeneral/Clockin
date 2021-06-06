package com.uoc.clockin.app.users.models.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uoc.clockin.app.commons.models.entity.Absence;
import com.uoc.clockin.app.commons.models.entity.DailyEntry;
import com.uoc.clockin.app.commons.models.entity.User;

@Service
public interface IUserService {
	
	public List<User> findAll();
	
	public User findById(Long id);

	public User findByUsername(String username);
	
	public User findByEmail(String email);
	
	public User findByUsernameOrEmail(String username, String email);
	
	public List<User> findByIdCompany(int idCompany);
	
	public List<User> findByValidated(Boolean validated);
	
	public List<User> findByTerm(int idCompany, String term);
	
	public List<User> findByIdCompanyAndValidated(int idCompany, Boolean validated);
	
	public User save(User user);
	
	public void deleteById(Long id);
			
}
