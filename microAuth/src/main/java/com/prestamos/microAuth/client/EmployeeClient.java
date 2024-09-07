package com.prestamos.microAuth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.prestamos.microAuth.model.Employee;

@FeignClient(name = "employee-service", url = "http://localhost:8081")
public interface EmployeeClient {

    @GetMapping("/api/employees/email/{email}")
    Employee getEmployeeByEmail(@PathVariable("email") String email);
}