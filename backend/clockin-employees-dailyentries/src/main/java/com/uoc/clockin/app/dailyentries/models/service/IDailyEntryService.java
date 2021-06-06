package com.uoc.clockin.app.dailyentries.models.service;

import java.sql.Date;
import java.util.List;

import com.uoc.clockin.app.commons.models.entity.DailyEntry;

public interface IDailyEntryService {
	
	public List<DailyEntry> findAll();

	public DailyEntry findById(Long id);
	
	public DailyEntry findActive(int codUser, Date date);
	
	public List<DailyEntry> findTodayUser(int codUser);
	
	public List<DailyEntry> findSevenDays(int codUser);
	
	public List<DailyEntry> findThirtyDays(int codUser);
	
	public List<DailyEntry> findByCodUser(int codUser);
	
	public List<DailyEntry> findByDate(Date date); 
	
	public List<DailyEntry> findBetweenEpochs(Long entryTime, Long departureTime); 
	
	public List<DailyEntry> findBetweenEpochsUser(Long entryTime, Long departureTime, int idUser); 
	
	public DailyEntry save(DailyEntry dailyEntry);
	
	public void deleteById(Long id);
	
}
