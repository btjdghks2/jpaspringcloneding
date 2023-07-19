package com.example.clonecoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = "com.example.clonecoding.repository")
public class ClonecodingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClonecodingApplication.class, args);
	}

}
