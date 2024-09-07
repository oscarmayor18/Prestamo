package com.prestamos.microAuth.model;

public class AuthRequest {
	private String email; // Email para autenticación
	private String password; // Contraseña para autenticación
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AuthRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public AuthRequest() {
	}

	
}
