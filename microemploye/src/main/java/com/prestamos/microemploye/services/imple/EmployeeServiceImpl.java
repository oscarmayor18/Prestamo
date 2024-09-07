package com.prestamos.microemploye.services.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.prestamos.microemploye.exception.DuplicateResourceException;
import com.prestamos.microemploye.exception.ResourceNotFoundException;
import com.prestamos.microemploye.models.Employee;
import com.prestamos.microemploye.repository.EmployeeRepository;
import com.prestamos.microemploye.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Crear un nuevo empleado
	@Override
	public Employee createEmployee(Employee employee) {
		// Verificar si el email ya está en uso
		if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
			throw new DuplicateResourceException("Email ya está en uso");
		}
		// Verificar si la cédula ya está en uso
		if (employeeRepository.findByCedule(employee.getCedule()).isPresent()) {
			throw new DuplicateResourceException("Cédula ya está en uso");
		}

		// Codificar la contraseña antes de guardarla
		String encodedPassword = passwordEncoder.encode(employee.getPassword());
		employee.setPassword(encodedPassword);

		return employeeRepository.save(employee);
	}

	// Obtener todos los empleados
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	// Obtener un empleado por ID
	@Override
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));
	}

	// Actualizar un empleado
	@Override
	public Employee updateEmployee(Employee employeeDetails) {
		Employee employee = employeeRepository.findById(employeeDetails.getId()).orElseThrow(
				() -> new ResourceAccessException("No se encontro el empleado con el id = " + employeeDetails.getId()));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setPhoneNumber(employeeDetails.getPhoneNumber());
		employee.setRole(employeeDetails.getRole());

		// Actualizar la contraseña solo si se proporciona una nueva
		if (employeeDetails.getPassword() != null && !employeeDetails.getPassword().isEmpty()) {
			employee.setPassword(passwordEncoder.encode(employeeDetails.getPassword()));
		}

		// Verificar si el nuevo email ya está en uso por otro empleado
		if (!employee.getEmail().equals(employeeDetails.getEmail())) {
			if (employeeRepository.findByEmail(employeeDetails.getEmail()).isPresent()) {
				throw new DuplicateResourceException("Email ya está en uso");
			}
			employee.setEmail(employeeDetails.getEmail());
		}

		// Verificar si la nueva cédula ya está en uso por otro empleado
		if (!employee.getCedule().equals(employeeDetails.getCedule())) {
			if (employeeRepository.findByCedule(employeeDetails.getCedule()).isPresent()) {
				throw new DuplicateResourceException("Cédula ya está en uso");
			}
			employee.setCedule(employeeDetails.getCedule());
		}

		return employeeRepository.save(employee);
	}

	// Eliminar un empleado
	@Override
	public void deleteEmployee(Long id) {
		Employee employee = getEmployeeById(id);
		employeeRepository.delete(employee);
	}

	@Override
	public Employee findByEmail(String email) {
		return employeeRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Empleado no encontrado con email: " + email));
	}

	@Override
	public Optional<Employee> findByCedule(String cedule) {
		return employeeRepository.findByCedule(cedule);
	}
}
