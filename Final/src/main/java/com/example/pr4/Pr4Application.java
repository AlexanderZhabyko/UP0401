package com.example.pr4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.pr4.repo")
public class Pr4Application {

	public static void main(String[] args) {
		SpringApplication.run(Pr4Application.class, args);
	}

}
