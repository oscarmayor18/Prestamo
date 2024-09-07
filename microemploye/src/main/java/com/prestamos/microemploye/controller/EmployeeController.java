package com.prestamos.microemploye.controller;
import com.prestamos.microemploye.models.Employee;
import com.prestamos.microemploye.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Crear un nuevo empleado
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    // Obtener todos los empleados
    @GetMapping
   // @PreAuthorize("hasRole('ADMIN')")  // Solo los administradores pueden ver todos los empleados
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // Obtener un empleado por ID
    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")  // Administradores y empleados pueden ver detalles de un empleado
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    // Actualizar un empleado
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employeeDetails) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeDetails));
    }

    // Eliminar un empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
    
    // Obtener un empleado por email
    @GetMapping("/email/{email}")
    @PreAuthorize("permitAll()")  // Permite acceso sin autenticación
    public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable String email) {
        return ResponseEntity.ok(employeeService.findByEmail(email));
    }
}