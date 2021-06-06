package com.uoc.clockin.app.commons.models.entity;

import java.util.ArrayList;
import java.util.List;

public class Message {
	
	public List<Absence> validated;
	public List<Absence> declined;
	public List<Absence> not_validated;
	public List<Absence> not_validated_company;
	
	public Message() {
		super();
		this.validated = new ArrayList<Absence>();
		this.declined = new ArrayList<Absence>();
		this.not_validated = new ArrayList<Absence>();
		this.not_validated_company = new ArrayList<Absence>();
	}
	
	public Message(List<Absence> validated, List<Absence> declined, List<Absence> not_validated, List<Absence> not_validated_company) {
		super();
		this.validated = validated;
		this.declined = declined;
		this.not_validated = not_validated;
		this.not_validated_company = not_validated_company;
	}

	public List<Absence> getValidated() {
		return validated;
	}

	public void setValidated(List<Absence> validated) {
		this.validated = validated;
	}

	public List<Absence> getNot_validated() {
		return not_validated;
	}

	public void setNot_validated(List<Absence> not_validated) {
		this.not_validated = not_validated;
	}	

	public List<Absence> getNot_validated_company() {
		return not_validated_company;
	}

	public void setNot_validated_company(List<Absence> not_validated_company) {
		this.not_validated_company = not_validated_company;
	}

	public List<Absence> getDeclined() {
		return declined;
	}

	public void setDeclined(List<Absence> declined) {
		this.declined = declined;
	}

	@Override
	public String toString() {
		return "Message [validated=" + validated + ", declined=" + declined + ", not_validated=" + not_validated
				+ ", not_validated_company=" + not_validated_company + "]";
	}	

}
