package com.uoc.clockin.app.commons.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "dailyentry")
public class DailyEntry implements Serializable{

	private static final long serialVersionUID = -8535816054299548814L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_daily")
	private Long id;
	@Column(name = "cod_user")
	private int codUser;
	@Column(name = "date_entry")
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name = "entry_time")
	private Long entryTime;
	@Column(name = "departure_time")
	private Long departureTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public int getCodUser() {
		return codUser;
	}

	public void setCodUser(int codUser) {
		this.codUser = codUser;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Long entryTime) {
		this.entryTime = entryTime;
	}

	public Long getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Long departureTime) {
		this.departureTime = departureTime;
	}

	@Override
	public String toString() {
		return "DailyEntry [id=" + id + ", codUser=" + codUser + ", date=" + date + ", entryTime=" + entryTime
				+ ", departureTime=" + departureTime + "]";
	}
	
}
