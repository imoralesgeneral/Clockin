package com.uoc.clockin.app.commons.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class User implements Serializable {

	private static final long serialVersionUID = 7454767363357240191L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_user")
	private Long id;
	@Column(name = "id_company")
	private int idCompany;
	@Column(unique = true, name = "username")
	private String username;
	@Column(length = 60, name = "password")
	private String password;
	@Column(name = "name")
	private String name;
	@Column(unique = true, name = "email")
	private String email;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "holidays")
	private int holidays;
	@Column(name = "salary_year")
	private Double salaryYear;
	@Column(name = "irpf")
	private Double irpf;
	@Column(name = "weekly_hours")
	private Double weeklyHours;
	@Column(name = "number_of_payments")
	private Double payments;
	/*@Column(name = "validated")
	private Boolean validated;*/
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns =  @JoinColumn(name = "cod_user"), 
	inverseJoinColumns = @JoinColumn(name = "id_role"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"cod_user","id_role"})})
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getHolidays() {
		return holidays;
	}

	public void setHolidays(int holidays) {
		this.holidays = holidays;
	}

	public Double getSalaryYear() {
		return salaryYear;
	}

	public void setSalaryYear(Double salaryYear) {
		this.salaryYear = salaryYear;
	}

	public Double getIrpf() {
		return irpf;
	}

	public void setIrpf(Double irpf) {
		this.irpf = irpf;
	}

	public Double getWeeklyHours() {
		return weeklyHours;
	}

	public void setWeeklyHours(Double weeklyHours) {
		this.weeklyHours = weeklyHours;
	}

	public Double getPayments() {
		return payments;
	}

	public void setPayments(Double payments) {
		this.payments = payments;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", idCompany=" + idCompany + ", username=" + username + ", password=" + password
				+ ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", holidays=" + holidays
				+ ", salaryYear=" + salaryYear + ", irpf=" + irpf + ", weeklyHours=" + weeklyHours + ", payments="
				+ payments + ", roles=" + roles + "]";
	}

}
