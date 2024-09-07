package com.prestamos.microemploye.security;


import com.prestamos.microemploye.models.Employee;
import com.prestamos.microemploye.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca al empleado por su email (que se usa como username)
        Employee employee = employeeService.findByEmail(username);
        
        // Crea y retorna un UserPrincipal basado en el empleado encontrado
        return UserPrincipal.create(employee);
    }
}