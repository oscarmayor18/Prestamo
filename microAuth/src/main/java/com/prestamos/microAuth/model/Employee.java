package com.prestamos.microAuth.model;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String cedule;
    private String email;
    private String phoneNumber;
    private EmployeeRole role;
    private String password; // Nuevo campo para la contraseña
    // Constructor vacío
    public Employee() {}

    // Constructor completo


    // Getters y setters
    public Long getId() {
        return id;
    }

    public Employee(Long id, String firstName, String lastName, String cedule, String email, String phoneNumber,
			EmployeeRole role, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cedule = cedule;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.password = password;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCedule() {
        return cedule;
    }

    public void setCedule(String cedule) {
        this.cedule = cedule;
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

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }
    
    

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	// Enum EmployeeRole
    public enum EmployeeRole {
        ADMIN,
        EMPLOYEE
    }

    // Equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(cedule, employee.cedule) &&
                Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cedule, email);
    }

    // ToString
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cedule='" + cedule + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                '}';
    }
}