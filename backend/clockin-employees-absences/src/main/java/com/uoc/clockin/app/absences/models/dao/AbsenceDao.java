package com.uoc.clockin.app.absences.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uoc.clockin.app.commons.models.entity.Absence;

public interface AbsenceDao extends CrudRepository<Absence, Long>{
	
	@Query(value = "SELECT * FROM ABSENCE WHERE COD_USER = ?1", nativeQuery = true)
	List<Absence> findByCodUser(int codUser);
	
	@Query(value = "SELECT * FROM ABSENCE WHERE TYPE = ?1", nativeQuery = true)
	List<Absence> findByType(int type);

	@Query(value = "SELECT * FROM ABSENCE WHERE VALIDATED IS NULL AND COD_USER = ?1", nativeQuery = true)
	List<Absence> findNotValidatedUser(int codUser);

	@Query(value = "SELECT * FROM ABSENCE WHERE VALIDATED IS NULL AND ID_COMPANY = ?1", nativeQuery = true)
	List<Absence> findNotValidatedCompany(int idCompany);
	
	@Query(value = "SELECT * FROM ABSENCE WHERE VALIDATED IS NULL AND COD_USER = ?1 AND ID_COMPANY = ?2", nativeQuery = true)
	List<Absence> findNotValidated(int codUser, int idCompany);
	
	@Query(value = "SELECT * FROM ABSENCE WHERE VALIDATED IS NOT NULL AND VALIDATED = true AND COD_USER = ?1 AND ID_COMPANY = ?2 ORDER BY START_TIME LIMIT 10", nativeQuery = true)
	List<Absence> findLastAbsences(int codUser, int idCompany);
	
	@Query(value = "SELECT HOLIDAYS FROM EMPLOYEE WHERE COD_USER = ?1", nativeQuery = true)
	Integer findNumberHolidays(int codUser);
	
	@Query(value = "SELECT * FROM ABSENCE WHERE COD_USER = ?1 AND END_TIME >= ?2 AND (VALIDATED IS NULL OR VALIDATED = true)", nativeQuery = true)
	List<Absence> findSpentHolidays(int codUser, long epochStart);

	@Query(value = "SELECT * FROM ABSENCE WHERE VALIDATED IS NOT NULL AND VALIDATED = false AND COD_USER = ?1 AND ID_COMPANY = ?2 ORDER BY START_TIME LIMIT 10", nativeQuery = true)
	List<Absence> findLastDeclinedAbsences(int codUser, int idCompany);

}
