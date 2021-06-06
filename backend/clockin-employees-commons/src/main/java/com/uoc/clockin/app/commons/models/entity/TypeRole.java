package com.uoc.clockin.app.commons.models.entity;

public enum TypeRole {

	ROLE_USER("Role_User"),
	ROLE_ADMIN("Role_Admin");
		
	private String type;
	
	private TypeRole(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
}
