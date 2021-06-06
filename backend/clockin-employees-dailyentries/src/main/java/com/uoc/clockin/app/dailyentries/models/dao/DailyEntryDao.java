package com.uoc.clockin.app.dailyentries.models.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uoc.clockin.app.commons.models.entity.DailyEntry;

public interface DailyEntryDao extends CrudRepository<DailyEntry, Long>{
	
	@Query(value = "SELECT * FROM DAILYENTRY WHERE COD_USER = ?1", nativeQuery = true)
	List<DailyEntry> findByCodUser(int codUser);
	
	@Query(value = "SELECT * FROM DAILYENTRY WHERE DATE_ENTRY = ?1", nativeQuery = true)
	List<DailyEntry> findByDate(Date date); //Date en java.util posible cambio a java.sql
	
	@Query(value = "SELECT * FROM DAILYENTRY WHERE COD_USER = ?1 AND DATE_ENTRY = CURRENT_DATE", nativeQuery = true)
	List<DailyEntry> findTodayUser(int codUser);
	
	@Query(value = "SELECT * FROM DAILYENTRY WHERE COD_USER = ?1 AND DATE_ENTRY > (CURRENT_DATE - INTERVAL '7 day') AND DATE_ENTRY <= CURRENT_DATE", nativeQuery = true)
	List<DailyEntry> findSevenDays(int codUser);
	
	@Query(value = "SELECT * FROM DAILYENTRY WHERE COD_USER = ?1 AND DATE_ENTRY > (CURRENT_DATE - INTERVAL '30 day') AND DATE_ENTRY <= CURRENT_DATE", nativeQuery = true)
	List<DailyEntry> findThirtyDays(int codUser);
	
	@Query(value = "SELECT * FROM DAILYENTRY WHERE ENTRY_TIME >= ?1 AND DEPARTURE_TIME <= ?2", nativeQuery = true)
	List<DailyEntry> findBetweenEpochs(Long entryTime, Long departureTime); 
	
	@Query(value = "SELECT * FROM DAILYENTRY WHERE ENTRY_TIME >= ?1 AND DEPARTURE_TIME <= ?2 AND COD_USER = ?3", nativeQuery = true)
	List<DailyEntry> findBetweenEpochsUser(Long entryTime, Long departureTime, int idUser); 
	
	@Query(value = "SELECT * FROM DAILYENTRY WHERE COD_USER = ?1 AND DATE_ENTRY = ?2 AND DEPARTURE_TIME = 0", nativeQuery = true)
	DailyEntry findActive(int codUser, Date date);

}
