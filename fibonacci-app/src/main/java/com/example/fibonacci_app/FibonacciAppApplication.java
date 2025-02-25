package com.example.fibonacci_app; // Defines the package name

import org.springframework.boot.SpringApplication; // Provides the entry point for the Spring Boot application
import org.springframework.boot.autoconfigure.SpringBootApplication; // Enables Spring Boot auto-configuration
import org.springframework.cache.annotation.EnableCaching; // Enables caching functionality

@SpringBootApplication // Marks this class as the main Spring Boot application
@EnableCaching // Enables caching across the application
public class FibonacciAppApplication {

	/**
	 * Main method: Entry point of the Spring Boot application.
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(FibonacciAppApplication.class, args); // Starts the Spring Boot application
	}
}
