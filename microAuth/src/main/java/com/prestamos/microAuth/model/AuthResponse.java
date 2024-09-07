package com.prestamos.microAuth.model;

public class AuthResponse {
    private String token; // Token JWT generado tras una autenticación exitosa

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AuthResponse(String token) {
		super();
		this.token = token;
	}

	public AuthResponse() {
		super();
	}

	
}
