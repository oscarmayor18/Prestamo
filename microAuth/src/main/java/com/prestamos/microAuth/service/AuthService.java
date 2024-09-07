package com.prestamos.microAuth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prestamos.microAuth.client.EmployeeClient;
import com.prestamos.microAuth.model.Employee;
import com.prestamos.microAuth.util.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticate(String email, String password) {
        Employee employee = employeeClient.getEmployeeByEmail(email);
        if (employee == null) {
            throw new BadCredentialsException("Employee not found");
        }
        if (!passwordEncoder.matches(password, employee.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return jwtUtil.generateToken(employee.getEmail(), employee.getRole().name());
    }
}