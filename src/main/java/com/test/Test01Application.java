package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.test.repository")
@EnableJpaRepositories(basePackages = "com.test.repository")
@EntityScan(basePackages = "com.test.entity")
public class Test01Application {
	public static void main(String[] args) {
		SpringApplication.run(Test01Application.class, args);
	}

}
