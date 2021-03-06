package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.entity.Employee;
import com.employee.exceptions.EmployeeBusinessException;
import com.employee.repository.EmployeeRepository;

@Transactional
@Service("service2")
@Profile(value = { "local", "dev", "prod", "qa" })
public class EmployeeServiceImpl2 implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public String saveEmployee(Employee employee) {
		employee.getAddress().setEmployee(employee);
		Employee employeeResponse = employeeRepository.save(employee);
		if (employeeResponse == null) {
			return "Data not saved";
		}
		return "Data saved Successfully";
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> response = employeeRepository.findAll();
		if (response == null) {
			throw new RuntimeException("Data is empty");
		}
		return response;
	}

	@Override
	public Employee getEmployeeById(Integer employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		if (!employee.isPresent()) {
			throw new EmployeeBusinessException("Data is not exists for given id");
		}
		return employee.get();
	}

	@Override
	public List<Employee> getEmployeeByName(String employeeName) {
		Optional<List<Employee>> employee = employeeRepository.findByEmployeeName(employeeName);
		if (!employee.isPresent()) {
			throw new EmployeeBusinessException("Data is not exists oe empty");
		}
		return employee.get();
	}

	@Override
	public Employee login(String loginId, String password) {
		// Optional<Employee> employee =
		// employeeRepository.findByLoginIdAndPassword(loginId, password);
		Optional<Employee> employee = employeeRepository.getByLoginIdAndPassword(loginId, password);
		if (!employee.isPresent()) {
			throw new EmployeeBusinessException("Data is not exists for login");
		}
		return employee.get();
	}

	@Transactional
	@Override
	public String updateEmployee(Employee employee) {
		Employee response = null;
		try {
			response = getEmployeeById(employee.getEmployeeId());
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}

		if (employee.getEmail() != null && !employee.getEmail().isBlank()) {
			response.setEmail(employee.getEmail());
		}
		if (employee.getEmployeeName() != null && !employee.getEmployeeName().isBlank()) {
			response.setEmployeeName(employee.getEmployeeName());
		}
		if (employee.getLoginId() != null && !employee.getLoginId().isBlank()) {
			response.setLoginId(employee.getLoginId());
		}
		if (employee.getMobileNumber() != null && !employee.getMobileNumber().isBlank()) {
			response.setMobileNumber(employee.getMobileNumber());
		}
		if (employee.getPassword() != null && !employee.getPassword().isBlank()) {
			response.setPassword(employee.getPassword());
		}
		employeeRepository.save(response);
		return "updated successsfully";
	}

	@Transactional
	@Override
	public String updateEmployeeName(String employeeName, Integer employeeId) {
		employeeRepository.updateName(employeeName, employeeId);
		return "updated name successfully";
	}

	@Override
	public String deleteEmployeeById(Integer employeeId) {
		Employee response = null;
		try {
			response = getEmployeeById(employeeId);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}
		// employeeRepository.deleteById(employeeId);
		employeeRepository.delete(response);
		return "deleted successfully";
	}

}
