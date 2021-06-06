package com.uoc.clockin.app.commons.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.uoc.clockin.app.commons.models.entity.User;

@Entity
@Table(name = "company")
public class Company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7211860236043772951L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_company")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "street")
	private String street;
	@Column(name = "city")
	private String city;
	@Column(name = "postal_code")
	private int postalCode;
	@Column(name = "country")
	private String country;
	@Column(name = "phone_number")
	private String phoneNumber;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "employee", joinColumns = @JoinColumn(name = "id_company"), 
	inverseJoinColumns = @JoinColumn(name = "cod_user"), uniqueConstraints = {
	@UniqueConstraint(columnNames = { "id_company", "cod_user" }) })
	private List<User> users;
 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", street=" + street + ", city=" + city + ", postalCode="
				+ postalCode + ", country=" + country + ", phoneNumber=" + phoneNumber + ", users=" + users + "]";
	}

}
