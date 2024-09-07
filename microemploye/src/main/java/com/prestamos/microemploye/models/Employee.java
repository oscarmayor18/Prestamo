package com.prestamos.microemploye.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "La cédula es obligatoria")
    @Column(nullable = false, unique = true)
    private String cedule;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    @Column(nullable = false, unique = true)
    private String email;
    
    @NotBlank(message = "La contraseña es obligatoria")
    @Column(nullable = false)
    private String password; // Nuevo campo para la contraseña

    @NotBlank(message = "El número de teléfono es obligatorio")
    @Pattern(regexp = "^\\+?[0-9]{10,13}$", message = "Número de teléfono inválido")
    @Column(nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeRole role;

	public Long getId() {
		return id;
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


	public Employee(Long id, @NotBlank(message = "El nombre es obligatorio") String firstName,
			@NotBlank(message = "El apellido es obligatorio") String lastName,
			@NotBlank(message = "La cédula es obligatoria") String cedule,
			@NotBlank(message = "El email es obligatorio") @Email(message = "El email debe ser válido") String email,
			@NotBlank(message = "La contraseña es obligatoria") String password,
			@NotBlank(message = "El número de teléfono es obligatorio") @Pattern(regexp = "^\\+?[0-9]{10,13}$", message = "Número de teléfono inválido") String phoneNumber,
			EmployeeRole role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cedule = cedule;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee() {
	}
    
    
}
