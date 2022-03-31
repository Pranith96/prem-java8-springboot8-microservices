package com.employee.service;

import java.util.List;

import com.employee.entity.Employee;

public interface EmployeeService {

	String saveEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(Integer employeeId);

	List<Employee> getEmployeeByName(String employeeName);

	Employee login(String loginId, String password);

}
