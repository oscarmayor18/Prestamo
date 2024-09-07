package com.prestamos.microemploye.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prestamos.microemploye.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	// Búsqueda por email
    Optional<Employee> findByEmail(String email);
    
    // Búsqueda por cédula
    Optional<Employee> findByCedule(String cedule);
}
