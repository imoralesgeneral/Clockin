package com.uoc.clockin.app.commons.models.entity;

public class Quantity {

	private double amount;
	private String description;
	private String unit;
	
	public Quantity() {
		super();
	}

	public Quantity(double amount, String description, String unit) {
		super();
		this.amount = amount;
		this.description = description;
		this.unit = unit;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
