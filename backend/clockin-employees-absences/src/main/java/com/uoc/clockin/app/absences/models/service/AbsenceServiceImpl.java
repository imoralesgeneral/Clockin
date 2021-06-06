package com.uoc.clockin.app.absences.models.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uoc.clockin.app.absences.models.dao.AbsenceDao;
import com.uoc.clockin.app.commons.models.entity.Absence;
import com.uoc.clockin.app.commons.models.entity.Message;
import com.uoc.clockin.app.commons.models.entity.TypeAbsence;

@Service
public class AbsenceServiceImpl implements IAbsenceService {

	@Autowired
	private AbsenceDao absenceDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Absence> findAll() {
		return (List<Absence>) absenceDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Absence findById(Long id) {
		return absenceDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Absence> findByCodUser(int codUser) {
		return (List<Absence>) absenceDao.findByCodUser(codUser);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Absence> findByType(int type) {
		return (List<Absence>) absenceDao.findByType(type);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Absence> findNotValidatedCompany(int idCompany) {
		return (List<Absence>) absenceDao.findNotValidatedCompany(idCompany);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Absence> findNotValidatedUser(int codUser) {
		return (List<Absence>) absenceDao.findNotValidatedUser(codUser);
	}

	@Override
	public Message findMessageUser(int codUser, int idCompany) {
		List<Absence> validated = (List<Absence>) absenceDao.findLastAbsences(codUser, idCompany);
		List<Absence> declined = (List<Absence>) absenceDao.findLastDeclinedAbsences(codUser, idCompany);
		List<Absence> not_validated = (List<Absence>) absenceDao.findNotValidated(codUser, idCompany);
		List<Absence> company = (List<Absence>) absenceDao.findNotValidatedCompany(idCompany);
		Message message = new Message(validated, declined, not_validated, company);
		return message;
	}
	
	@Override
	public Long findRestHolidays(int codUser) {
		LocalDate startYear = LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1);
		long epochStartYear = startYear.toEpochDay();
		int totalHolidays = (int) absenceDao.findNumberHolidays(codUser);
		List<Absence> spentHolidays = (List<Absence>) absenceDao.findSpentHolidays(codUser, epochStartYear);
		int spent = 0;
		for(Absence absence : spentHolidays) {
			if(absence.getType().equals(TypeAbsence.MEDICAL_LEAVE)) continue;
			long start = absence.getStartTime();
			long end = absence.getEndTime();
			spent += numberDaysHolidays(start, end);
		}
		long rest = totalHolidays - spent;
		if(rest > 0) {
			return rest;
		}
		return 0L;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Absence> findSpentHolidays(int codUser, long epochStart) {
		return (List<Absence>) absenceDao.findSpentHolidays(codUser, epochStart);
	}
	
	private long numberDaysHolidays(long start, long end) {
		int number = 0;
		LocalDate startYear = LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1);
		long epochStartYear = startYear.toEpochDay();
		long difer = 0;
		if(start >= epochStartYear) {
			difer = end - start;
		} else {
			difer = end - epochStartYear;
		}		
		return (difer / 86400000);
	}

	@Override
	@Transactional
	public Absence save(Absence absence) {
		return absenceDao.save(absence);
	}	

	@Override
	@Transactional
	public void deleteById(Long id) {
		absenceDao.deleteById(id);
	}
	
}
