package com.employee.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "employee_table")
@ApiModel(description = "Details About the Employee")
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	@ApiModelProperty(notes = "The Unique Employee Id as primary key")
	private Integer employeeId;
	@Column(name = "employee_name")
	@ApiModelProperty(notes = "employee name as string")
	private String employeeName;
	@Column(name = "mobile_number")
	@ApiModelProperty(notes = "mobile numder as a string")
	private String mobileNumber;
	@Column(name = "email_id")
	@ApiModelProperty(notes = "email as string")
	private String email;
	@Column(name = "login_id", unique = true, nullable = false)
	@ApiModelProperty(notes = "The Unique login id")
	private String loginId;
	@Column(name = "password")
	@ApiModelProperty(notes = "The Password as string")
	private String password;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Company.class)
	@JoinColumn(name = "company_id")
	private Company company;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "employee_project", joinColumns = { @JoinColumn(name = "employeeId") }, inverseJoinColumns = {
			@JoinColumn(name = "projectId") })
	private List<Project> project;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Project> getProject() {
		return project;
	}

	public void setProject(List<Project> project) {
		this.project = project;
	}

}
