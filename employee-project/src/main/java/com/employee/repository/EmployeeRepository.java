package com.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<List<Employee>> findByEmployeeName(String employeeName);

	// Optional<Employee> findByLoginIdAndPassword(String loginId, String password);

	// @Query("select e from Employee e where e.loginId=:loginId and
	// e.password=:password")
	// Optional<Employee> getByLoginIdAndPassword(String loginId, String password);

	@Query("select e from Employee e where e.loginId=?1 and e.password=?2")
	Optional<Employee> getByLoginIdAndPassword(String loginId, String password);

	@Modifying
	@Query("Update Employee e set e.employeeName=:employeeName where e.employeeId=:employeeId")
	void updateName(String employeeName, Integer employeeId);

	// @Query(value = "select * from employee_table where login_id=:loginId and
	// password=:password",nativeQuery = true)
	// Optional<Employee> getByLoginIdAndPassword(String loginId, String password);

}
