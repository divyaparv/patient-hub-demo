package com.patient.hub.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication

public class PatientHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientHubApplication.class, args);
	}

}
