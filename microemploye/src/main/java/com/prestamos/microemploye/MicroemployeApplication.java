package com.prestamos.microemploye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroemployeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroemployeApplication.class, args);
	}

}
