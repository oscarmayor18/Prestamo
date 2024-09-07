package com.prestamos.microeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroeurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroeurekaserverApplication.class, args);
	}

}
