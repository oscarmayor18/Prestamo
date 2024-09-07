package com.prestamos.microCustomer.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "clients")
public class Client {
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

    @NotBlank(message = "El número de teléfono es obligatorio")
    @Pattern(regexp = "^\\+?[0-9]{10,13}$", message = "Número de teléfono inválido")
    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Long assignedEmployeeId;

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

	public Long getAssignedEmployeeId() {
		return assignedEmployeeId;
	}

	public void setAssignedEmployeeId(Long assignedEmployeeId) {
		this.assignedEmployeeId = assignedEmployeeId;
	}

	public Client(Long id, @NotBlank(message = "El nombre es obligatorio") String firstName,
			@NotBlank(message = "El apellido es obligatorio") String lastName,
			@NotBlank(message = "La cédula es obligatoria") String cedule,
			@NotBlank(message = "El email es obligatorio") @Email(message = "El email debe ser válido") String email,
			@NotBlank(message = "El número de teléfono es obligatorio") @Pattern(regexp = "^\\+?[0-9]{10,13}$", message = "Número de teléfono inválido") String phoneNumber,
			Long assignedEmployeeId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cedule = cedule;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.assignedEmployeeId = assignedEmployeeId;
	}

	public Client() {
	}

    
}
