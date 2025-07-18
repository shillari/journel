package com.project.journel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.project.journel")
@EnableJpaRepositories("com.project.journel")
public class JournelApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournelApplication.class, args);
	}

}
