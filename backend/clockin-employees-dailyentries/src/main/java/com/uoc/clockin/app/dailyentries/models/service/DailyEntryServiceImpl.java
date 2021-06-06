package com.uoc.clockin.app.dailyentries.models.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uoc.clockin.app.dailyentries.models.dao.DailyEntryDao;
import com.uoc.clockin.app.commons.models.entity.DailyEntry;

@Service
public class DailyEntryServiceImpl implements IDailyEntryService{

	@Autowired
	private DailyEntryDao dailyEntryDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<DailyEntry> findAll() {
		return (List<DailyEntry>) dailyEntryDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public DailyEntry findById(Long id) {
		return dailyEntryDao.findById(id).orElse(null);
	}
	
	@Override
	public List<DailyEntry> findTodayUser(int codUser) {
		return dailyEntryDao.findTodayUser(codUser);
	}
	
	@Override
	public List<DailyEntry> findSevenDays(int codUser) {
		return dailyEntryDao.findSevenDays(codUser);
	}
	
	@Override
	public List<DailyEntry> findThirtyDays(int codUser) {
		return dailyEntryDao.findThirtyDays(codUser);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DailyEntry> findByCodUser(int codUser) {
		return dailyEntryDao.findByCodUser(codUser);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DailyEntry> findByDate(Date date) {
		return dailyEntryDao.findByDate(date);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DailyEntry> findBetweenEpochs(Long entryTime, Long departureTime) {
		return dailyEntryDao.findBetweenEpochs(entryTime, departureTime);
	}
	
	@Override
	public List<DailyEntry> findBetweenEpochsUser(Long entryTime, Long departureTime, int idUser) {
		return dailyEntryDao.findBetweenEpochsUser(entryTime, departureTime, idUser);
	}

	@Override
	@Transactional
	public DailyEntry save(DailyEntry dailyEntry) {
		return dailyEntryDao.save(dailyEntry);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		dailyEntryDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public DailyEntry findActive(int codUser, Date date) {
		return dailyEntryDao.findActive(codUser, date);
	}

}
