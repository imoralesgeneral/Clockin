package com.uoc.clockin.app.dailyentries.controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uoc.clockin.app.commons.models.entity.DailyEntry;
import com.uoc.clockin.app.commons.models.entity.Quantity;
import com.uoc.clockin.app.commons.models.entity.Utils;
import com.uoc.clockin.app.dailyentries.models.service.IDailyEntryService;

@RefreshScope
@RestController
public class DailyEntryController {

	@Autowired
	private IDailyEntryService dailyEntryService;
	private Utils util = new Utils();

	@GetMapping("/api/entries/")
	public List<DailyEntry> getEntries() {
		return dailyEntryService.findAll();
	}

	@GetMapping("/api/entries/id/{id}")
	public DailyEntry getEntry(@PathVariable Long id) {
		return dailyEntryService.findById(id);
	}

	@GetMapping("/api/entries/active/{codUser}")
	public DailyEntry getActive(@PathVariable int codUser) {
		Date date = Date.valueOf(LocalDate.now());
		return dailyEntryService.findActive(codUser, date);
	}

	@GetMapping("/api/entries/coduser/{codUser}")
	public List<DailyEntry> getEntriesByCodUser(@PathVariable int codUser) {
		return dailyEntryService.findByCodUser(codUser);
	}

	@GetMapping("/api/entries/today/{codUser}")
	public List<DailyEntry> getEntriesTodayByCodUser(@PathVariable int codUser) {
		Date date = Date.valueOf(LocalDate.now().minusDays(1));
		DailyEntry active = dailyEntryService.findActive(codUser, date);
		List<DailyEntry> entries = dailyEntryService.findTodayUser(codUser);
		if(active != null) entries.add(active);
		return entries;
	}

	@SuppressWarnings("unlikely-arg-type")
	@GetMapping("/api/entries/week/{codUser}")
	public List<Quantity> getHoursWorkedLastWeek(@PathVariable int codUser) {
		List<DailyEntry> entries = dailyEntryService.findSevenDays(codUser);
		Map<Long, Double> time = initMapXDays(7);
		for (DailyEntry entry : entries) {
			if (entry.getDate() != null) {
				if (time.get(entry.getDate().getTime()) != null 
						&& entry.getDepartureTime() != null && entry.getDepartureTime() > 0) {
					double secondsNew = entry.getDepartureTime() - entry.getEntryTime();
					double secondsHist = time.get(entry.getDate().getTime());
					Double secs = secondsHist + secondsNew;
					time.put(entry.getDate().getTime(), secs);
				}
			}
		}
		List<Quantity> quantity = new ArrayList<>();
		time.forEach((k, v) -> {
			Quantity q = new Quantity(v / 3600000, k.toString(), "hours");
			quantity.add(q);
		});
		return quantity;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@GetMapping("/api/entries/month/{codUser}")
	public List<Quantity> getHoursWorkedLastMonth(@PathVariable int codUser) {
		List<DailyEntry> entries = dailyEntryService.findThirtyDays(codUser);
		Map<Long, Double> time = initMapXDays(30);
		for (DailyEntry entry : entries) {
			if (entry.getDate() != null) {
				if (time.get(entry.getDate().getTime()) != null 
						&& entry.getDepartureTime() != null && entry.getDepartureTime() > 0) {
					double secondsNew = entry.getDepartureTime() - entry.getEntryTime();
					double secondsHist = time.get(entry.getDate().getTime());
					Double secs = secondsHist + secondsNew;
					time.put(entry.getDate().getTime(), secs);
				}
			}
		}
		List<Quantity> quantity = new ArrayList<>();
		time.forEach((k, v) -> {
			Quantity q = new Quantity(v / 3600000, k.toString(), "hours");
			quantity.add(q);
		});
		return quantity;
	}

	private Map<Long, Double> initMapXDays(int numberOfDays) {
		Map<Long, Double> time = new HashMap<>();
		for (int i = 0; i < numberOfDays; i++) {
			LocalDate ld = LocalDate.now().minusDays(i);
			java.util.Date date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
			time.put(date.getTime(), (double) 0);
		}
		return time;
	}

	@GetMapping("/api/entries/worked/{month}/{year}/{codUser}")
	public Quantity getHoursWorked(@PathVariable int month, @PathVariable int year, @PathVariable int codUser) {
		long start = util.getEpochStartMonth(year, month);
		long end = util.getEpochEndMonth(year, month);
		List<DailyEntry> dailys = dailyEntryService.findBetweenEpochs(start, end);
		double hours = util.getHoursWorked(dailys);
		Quantity quantity = new Quantity(hours, "hours worked in a month", "hours");
		return quantity;
	}
	
	

	@GetMapping("/api/entries/date/{date}")
	public List<DailyEntry> getEntriesByDate(@PathVariable String date) {
		String fecString = date;
		Date fecFormatoDate = null;
		try {
			SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", new Locale("es", "ES"));
			fecFormatoDate = new Date(sdf.parse(fecString).getTime());
		} catch (Exception ex) {
			// System.out.println("Error al obtener el formato de la fecha/hora: " +
			// ex.getMessage());
		}
		return dailyEntryService.findByDate(fecFormatoDate);
	}

	@GetMapping("/api/entries/epoch/{entryTime}/{departureTime}")
	public List<DailyEntry> getEntriesBetweenEpochs(@PathVariable Long entryTime, @PathVariable Long departureTime) {
		return dailyEntryService.findBetweenEpochs(entryTime, departureTime);
	}
	
	@GetMapping("/api/entries/epoch/{idUser}/{entryTime}/{departureTime}")
	public List<DailyEntry> getEntriesBetweenEpochsUser(@PathVariable int idUser, @PathVariable Long entryTime, @PathVariable Long departureTime) {
		return dailyEntryService.findBetweenEpochsUser(entryTime, departureTime, idUser);
	}

	@PostMapping("/api/entries/create/{codUser}")
	@ResponseStatus(HttpStatus.CREATED)
	public DailyEntry create(@PathVariable int codUser) {
		DailyEntry dailyEntry = new DailyEntry();
		dailyEntry.setCodUser(codUser);
		dailyEntry.setDate(util.getDate());
		dailyEntry.setEntryTime(util.getEpoch());
		dailyEntry.setDepartureTime(0L);
		Date date = Date.valueOf(LocalDate.now());
		DailyEntry de = dailyEntryService.findActive(codUser, date);
		if (de == null) {
			return dailyEntryService.save(dailyEntry);
		}
		return new DailyEntry();
	}

	@PutMapping("/api/entries/update/{codUser}")
	@ResponseStatus(HttpStatus.CREATED)
	public DailyEntry update(@PathVariable int codUser) {
		Date date = Date.valueOf(LocalDate.now());
		Date dateYest = Date.valueOf(LocalDate.now().minusDays(1));
		DailyEntry de = dailyEntryService.findActive(codUser, date);
		DailyEntry deYest = dailyEntryService.findActive(codUser, dateYest);
		if (de != null) {
			de.setDepartureTime(util.getEpoch());
			return dailyEntryService.save(de);
		} else if(deYest != null) {
			deYest.setDepartureTime(util.getEpoch());
			return dailyEntryService.save(deYest);
		}
		return new DailyEntry(); // TODO devolver vacio
	}

	@DeleteMapping("/api/entries/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		dailyEntryService.deleteById(id);
	}

}
