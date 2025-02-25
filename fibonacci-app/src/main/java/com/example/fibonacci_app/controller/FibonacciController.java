package com.example.fibonacci_app.controller; // Defines the package for organization

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable; // Used for caching results
import org.springframework.web.bind.annotation.*; // Import Spring annotations for REST API

@RestController // Marks this class as a REST Controller (Handles HTTP Requests)
@RequestMapping("/api/fibonacci") // Sets the base URL for this API
public class FibonacciController {

    // Logger to track requests and cache behavior
    private static final Logger logger = LoggerFactory.getLogger(FibonacciController.class);

    /**
     * Handles GET requests to fetch the Fibonacci number of a given input.
     * Example: GET http://localhost:8080/api/fibonacci/10 → Returns 55
     * 
     * @param number The input number for Fibonacci calculation.
     * @return The Fibonacci number at position 'number'.
     */
    @GetMapping("/{number}") // Maps HTTP GET requests to this method
    @Cacheable("fibonacciCache") // Caches the final result (only) to improve performance
    public long getFibonacci(@PathVariable int number) {
        // Check if the number is negative and return an error
        if (number < 0) {
            throw new IllegalArgumentException("Number must be non-negative.");
        }

        // Log when a Fibonacci number is being calculated
        logger.info("Calculating Fibonacci for number: {}", number);
        long result = calculateFibonacci(number);

        // Log when a Fibonacci result is returned (either from cache or fresh
        // calculation)
        logger.info("Returning Fibonacci result: {} for number: {}", result, number);
        return result;
    }

    /**
     * Recursively calculates the Fibonacci number.
     * Fibonacci sequence:
     * - F(0) = 0
     * - F(1) = 1
     * - F(n) = F(n-1) + F(n-2)
     * 
     * @param n The Fibonacci sequence position.
     * @return The Fibonacci number at position n.
     */
    private long calculateFibonacci(int n) {
        // Base cases: F(0) = 0, F(1) = 1
        if (n <= 1) {
            return n;
        }

        // Recursive formula: F(n) = F(n-1) + F(n-2)
        return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
    }
}
