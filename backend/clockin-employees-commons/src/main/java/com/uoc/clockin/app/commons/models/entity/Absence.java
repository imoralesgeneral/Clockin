package com.uoc.clockin.app.commons.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "absence")
public class Absence implements Serializable {

	private static final long serialVersionUID = 3440617490442891668L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_absence")
	private Long id;
	@Column(name = "cod_user")
	private int codUser;
	@Column(name = "id_company")
	private int idCompany;
	@Column(name = "type")
	private TypeAbsence type;
	@Column(name = "start_time")
	private Long startTime;
	@Column(name = "end_time")
	private Long endTime;
	@Column(name = "validated")
	private Boolean validated;

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
	
	public int getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	public TypeAbsence getType() {
		return type;
	}

	public void setType(TypeAbsence type) {
		this.type = type;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Boolean isValidated() {
		return validated;
	}

	public void setValidated(Boolean validated) {
		this.validated = validated;
	}

	@Override
	public String toString() {
		return "Absence [id=" + id + ", codUser=" + codUser + ", idCompany=" + idCompany + ", type=" + type
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", validated=" + validated + "]";
	}

}
