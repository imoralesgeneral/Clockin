package com.uoc.clockin.app.commons.models.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class Utils {
	
	public Date getDate() {
		java.util.Date utilDate = new java.util.Date();
		Date date = new Date(utilDate.getTime());
		return date;
	}

	public long getEpoch() {
		java.util.Date utilDate = new java.util.Date();
		long epoch = utilDate.getTime();
		return epoch;
	}
	
	public long getEpochStartMonth(int year, int month) {
		LocalDate ld = LocalDate.of(year, month, 1);
		System.out.println("start: "+ld+" epoch: "+ld.toEpochDay());
		java.util.Date date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
		System.out.println("start 2: "+date+" epoch2: "+date.getTime());
		return date.getTime();
	}
	
	public long getEpochEndMonth(int year, int month) {
		LocalDate ld = LocalDate.of(year, month+1, 1);
		System.out.println("end: "+ld+" epoch: "+ld.toEpochDay());
		java.util.Date date =  Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
		System.out.println("end 2: "+date+" epoch2: "+date.getTime());
		return date.getTime();
	}
	
	public double getHoursWorked(List<DailyEntry> dailys) {
		double hours = 0;
		double secs = 0;
		for(DailyEntry de : dailys) {
			System.out.println("entry: "+de);
			if(de.getEntryTime() != 0 && de.getDepartureTime() != 0) {
				long accumulated = (de.getDepartureTime() - de.getEntryTime()) / 1000;
				secs += accumulated;
			}
		}
		hours = secs / 3600;
		return hours;
	}
	
}
