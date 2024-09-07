package com.prestamos.microemploye.services;

import java.util.List;
import java.util.Optional;

import com.prestamos.microemploye.models.Employee;

public interface EmployeeService {
	Employee createEmployee(Employee employee);
	Employee updateEmployee( Employee employee);
	void deleteEmployee(Long id);
	List<Employee> getAllEmployees();
	Employee findByEmail(String email);
    Optional<Employee> findByCedule(String cedule);
	Employee getEmployeeById(Long id);
    
}
