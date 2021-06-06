package com.uoc.clockin.app.users.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uoc.clockin.app.commons.models.entity.Absence;
import com.uoc.clockin.app.commons.models.entity.DailyEntry;
import com.uoc.clockin.app.commons.models.entity.User;

public interface UserDao extends CrudRepository<User, Long>{
	
	@Query(value = "SELECT * FROM EMPLOYEE WHERE USERNAME = ?1", nativeQuery = true)
	public User findByUsername(String username);
	
	@Query(value = "SELECT * FROM EMPLOYEE WHERE EMAIL = ?1", nativeQuery = true)
	public User findByEmail(String email);
	
	@Query(value = "SELECT * FROM EMPLOYEE WHERE USERNAME = ?1 OR EMAIL = ?2", nativeQuery = true)
	public User findByUsernameOrEmail(String username, String email);
	
	@Query(value = "SELECT * FROM EMPLOYEE WHERE ID_COMPANY = ?1", nativeQuery = true)
	public List<User> findByIdCompany(int idCompany);
	
	@Query(value = "SELECT * FROM EMPLOYEE WHERE ID_COMPANY = ?1 AND (USERNAME LIKE %?2% OR NAME LIKE %?2% OR EMAIL LIKE %?2%)", nativeQuery = true)
	public List<User> findByTerm(int idCompany, String term);
	
	@Query(value = "SELECT * FROM EMPLOYEE WHERE VALIDATED = ?1", nativeQuery = true)
	public List<User> findByValidated(Boolean validated);
	
	@Query(value = "SELECT * FROM EMPLOYEE WHERE ID_COMPANY = ?1 AND VALIDATED = ?2", nativeQuery = true)
	public List<User> findByIdCompanyAndValidated(int idCompany, Boolean validated);
			
}
