package com.project.journel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JournelApplication {

	public static void main(String[] args) {
		System.out.println("POSTGRES_USERNAME: " + System.getenv("POSTGRES_USERNAME"));

		SpringApplication.run(JournelApplication.class, args);
	}

}
