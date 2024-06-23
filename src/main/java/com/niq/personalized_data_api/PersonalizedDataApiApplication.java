package com.niq.personalized_data_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PersonalizedDataApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalizedDataApiApplication.class, args);
	}

}
