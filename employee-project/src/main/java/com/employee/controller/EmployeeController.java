package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	//@Qualifier("service1")
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/save")
	@ApiOperation(value = "Employee account Creation API", notes = "Please provide all the info for Employee account creation", response = Employee.class)
	public ResponseEntity<String> createEmployeeAccount(@RequestBody Employee employee) {
		String response = employeeService.saveEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/get/all")
	@ApiOperation(value = "Employee Details Fetch API", notes = "All the list of employees will be fetched", response = Employee.class)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> response = employeeService.getAllEmployees();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/get/{employeeId}")
	@ApiOperation(value = "Employee Details fetch by Id API", notes = "Please provide all the info for Employee Deatils fetch", response = Employee.class)
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("employeeId") Integer employeeId) {
		Employee response = employeeService.getEmployeeById(employeeId);
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmail(response.getEmail());
		employeeDto.setEmployeeName(response.getEmployeeName());
		employeeDto.setMobileNumber(response.getMobileNumber());
		employeeDto.setCity(response.getAddress().getCity());
		employeeDto.setLocality(response.getAddress().getLocality());
		employeeDto.setPincode(response.getAddress().getPincode());
		employeeDto.setPlotNo(response.getAddress().getPlotNo());
		employeeDto.setState(response.getAddress().getState());
		return ResponseEntity.status(HttpStatus.OK).body(employeeDto);
	}

	@GetMapping("/get/id/{employeeId}")
	public ResponseEntity<Employee> getEmployeeDetails(@PathVariable("employeeId") Integer employeeId) {
		Employee response = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/get")
	public ResponseEntity<List<Employee>> getEmployeeByName(@RequestParam("employeeName") String employeeName) {
		List<Employee> response = employeeService.getEmployeeByName(employeeName);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/login/{loginId}/{password}")
	public ResponseEntity<Employee> employeeLogin(@PathVariable("loginId") String loginId,
			@PathVariable("password") String password) {
		Employee response = employeeService.login(loginId, password);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
		String response = employeeService.updateEmployee(employee);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/update/name/{employeeId}")
	public ResponseEntity<String> updateEmployeeName(@RequestParam("employeeName") String employeeName,
			@PathVariable("employeeId") Integer employeeId) {
		String response = employeeService.updateEmployeeName(employeeName, employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/delete/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") Integer employeeId) {
		String response = employeeService.deleteEmployeeById(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
