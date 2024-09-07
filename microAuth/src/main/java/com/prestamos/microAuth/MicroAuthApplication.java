package com.prestamos.microAuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // Habilita Feign para llamadas entre microservicios
@EnableDiscoveryClient
public class MicroAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroAuthApplication.class, args);
	}

}
