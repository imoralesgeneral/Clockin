package com.uoc.clockin.app.commons.models.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaySlip {

	private double irpf;
	private double salaryYear;
	private double workedHours;
	private double salaryBefore;
	private double salaryAfter;
	private double hoursAbsences;
	private Map<String, Double> discounts;
	private Map<String, Double> pluses;

	public PaySlip(User user, List<DailyEntry> entriesMonth, List<Absence> absencesMonth, long epochS, long epochE) {
		this.irpf = user.getIrpf();
		this.salaryYear = user.getSalaryYear();
		this.salaryBefore = getSalaryBefore(user);
		this.discounts = new HashMap<String, Double>();
		this.pluses = new HashMap<String, Double>();
		produceDiscounts(user, entriesMonth, absencesMonth, epochS, epochE);
		calculateWorkedHours(user, entriesMonth);
		this.salaryAfter = calculateSalary();
		
	}

	private double calculateSalary() {
		double salary = salaryAfter;
		for (Map.Entry<String, Double> entry : discounts.entrySet()) {
			if(entry.getKey().equalsIgnoreCase("Minus hours")) {
				continue;
			}
			salary += entry.getValue();
		}
		for (Map.Entry<String, Double> entry : pluses.entrySet()) {
			if(entry.getKey().equalsIgnoreCase("Extra hours")) {
				salary += entry.getValue();
			} 
		}		
		double discount = (salary * irpf) / 100;
		discounts.put("IRPF "+irpf+"%", discount);
		return salary - discount;
	}

	private double getSalaryBefore(User user) {
		double salaryMonth = user.getSalaryYear() / user.getPayments();
		return salaryMonth;
	}

	private void produceDiscounts(User user, List<DailyEntry> entriesMonth, List<Absence> absencesMonth, long epochS,
			long epochE) {
		Map<String, Long> disc = new HashMap<>();
		if (absencesMonth.size() > 0) {
			getDiscountsAbsences(user, absencesMonth, epochS, epochE);
		}
	}

	private void calculateWorkedHours(User user, List<DailyEntry> entriesMonth) {
		if (entriesMonth.size() > 0) {
			calculateHours(user, entriesMonth);
		}
	}

	private void getDiscountsAbsences(User user, List<Absence> absencesMonth, long epochS, long epochE) {
		double hoursPerMonth = (user.getWeeklyHours() * 4.345);
		double salaryPerHour = salaryBefore / hoursPerMonth;
		for (Absence absence : absencesMonth) {
			if (absence.getType() != TypeAbsence.MEDICAL_LEAVE) {
				continue;
			}
			long startAbsence = absence.getStartTime();
			long endAbsence = absence.getEndTime();
			if (startAbsence < epochS) {
				startAbsence = epochS;
			}
			if (endAbsence > epochE) {
				endAbsence = epochE;
			}
			double numberHoursAbsence = getHours(startAbsence, endAbsence) / (user.getWeeklyHours()/5);
			hoursAbsences += numberHoursAbsence;
			LocalDate dateS = Instant.ofEpochMilli(startAbsence).atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate dateE = Instant.ofEpochMilli(endAbsence).atZone(ZoneId.systemDefault()).toLocalDate();
			String desc = absence.getType().toString() + " ==> from: " + dateS + " to: " + dateE;
			discounts.put(desc, numberHoursAbsence * (salaryPerHour * 0.5));
		}
	}

	private void calculateHours(User user, List<DailyEntry> entriesMonth) {
		double hoursPerMonth = (user.getWeeklyHours() * 4.345);
		double salaryPerHour = salaryBefore / hoursPerMonth;
		for (DailyEntry entry : entriesMonth) {
			double numberHours = getHours(entry.getEntryTime(), entry.getDepartureTime());
			workedHours += numberHours;
		}
		double hoursReal = hoursPerMonth - hoursAbsences;
		if (workedHours > hoursReal) {
			double numberExtraHours = workedHours - hoursReal;
			double payExtra = numberExtraHours * salaryPerHour;
			pluses.put("Extra hours", payExtra);
		} else if (workedHours < hoursReal) {
			double numberMinusHours = hoursReal - workedHours;
			double payMinus = numberMinusHours * salaryPerHour;
			discounts.put("Minus hours", payMinus);
		}
		this.salaryAfter = salaryPerHour*workedHours;
	}

	private double getHours(long epochS, long epochE) {
		double epochTotal = epochE - epochS;
		return epochTotal / 3600000;
	}
	
	public double getIrpf() {
		return irpf;
	}

	public void setIrpf(double irpf) {
		this.irpf = irpf;
	}

	public double getSalaryYear() {
		return salaryYear;
	}

	public void setSalaryYear(double salaryYear) {
		this.salaryYear = salaryYear;
	}

	public double getWorkedHours() {
		return workedHours;
	}

	public void setWorkedHours(double workedHours) {
		this.workedHours = workedHours;
	}

	public double getSalaryBefore() {
		return salaryBefore;
	}

	public void setSalaryBefore(double salaryBefore) {
		this.salaryBefore = salaryBefore;
	}

	public double getSalaryAfter() {
		return salaryAfter;
	}

	public void setSalaryAfter(double salaryAfter) {
		this.salaryAfter = salaryAfter;
	}

	public double getHoursAbsences() {
		return hoursAbsences;
	}

	public void setHoursAbsences(double hoursAbsences) {
		this.hoursAbsences = hoursAbsences;
	}

	public Map<String, Double> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(Map<String, Double> discounts) {
		this.discounts = discounts;
	}

	public Map<String, Double> getPluses() {
		return pluses;
	}

	public void setPluses(Map<String, Double> pluses) {
		this.pluses = pluses;
	}

}
