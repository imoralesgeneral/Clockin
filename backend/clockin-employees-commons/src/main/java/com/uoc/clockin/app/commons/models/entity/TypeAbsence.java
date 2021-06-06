package com.uoc.clockin.app.commons.models.entity;

public enum TypeAbsence {

	PERSONAL_MATTERS("Personal Matters"),
	HOLIDAYS("Holidays"),
	MEDICAL_LEAVE("Medical leave");
		
	private String type;
	
	private TypeAbsence(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
}
