package com.uoc.clockin.app.absences.models.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uoc.clockin.app.commons.models.entity.Absence;
import com.uoc.clockin.app.commons.models.entity.Message;

@Service
public interface IAbsenceService {
	
	public List<Absence> findAll();
	
	public List<Absence> findByCodUser(int codUser);
	
	public List<Absence> findByType(int type);
	
	public List<Absence> findNotValidatedCompany(int idCompany);
	
	public List<Absence> findNotValidatedUser(int codUser);
	
	public List<Absence> findSpentHolidays(int codUser, long epochStart);
	
	public Message findMessageUser(int codUser, int idCompany);
	
	public Long findRestHolidays(int codUser);
	
	public Absence findById(Long id);
	
	public Absence save(Absence absence);
	
	public void deleteById(Long id);

}
