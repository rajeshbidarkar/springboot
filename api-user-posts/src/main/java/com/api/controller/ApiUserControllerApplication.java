package com.api.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ComponentScan("com.api")
public class ApiUserControllerApplication {
	public static void main(String[] args) {
		
		SpringApplication.run(ApiUserControllerApplication.class, args);
	}
}
