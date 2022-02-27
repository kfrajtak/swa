package org.springframework.samples.petclinic.vets.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VetsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetsServiceApplication.class, args);
	}

}
