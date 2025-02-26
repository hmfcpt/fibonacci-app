package com.example.fibonacci_app.controller;

import com.example.fibonacci_app.service.FibonacciService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;

/**
 * REST Controller for handling Fibonacci API requests.
 * It validates input, enforces a maximum index, and returns the formatted
 * Fibonacci number.
 */
@RestController
@RequestMapping("/api/fibonacci")
public class FibonacciController {

    private static final Logger logger = LoggerFactory.getLogger(FibonacciController.class);
    private final FibonacciService fibonacciService;

    /**
     * Constructor-based Dependency Injection of FibonacciService.
     *
     * @param fibonacciService The service handling Fibonacci calculations.
     */
    public FibonacciController(FibonacciService fibonacciService) {
        this.fibonacciService = fibonacciService;
    }

    /**
     * Handles GET requests to compute the Fibonacci number at a given index.
     * The allowed range is 0 to 1,000,000.
     *
     * @param number The Fibonacci index (as an integer).
     * @return The formatted Fibonacci number as a String.
     */
    @GetMapping("/{number}")
    public String getFibonacci(@PathVariable int number) {
        // Validate input.
        if (number < 0) {
            throw new IllegalArgumentException("Number must be non-negative.");
        }
        if (number > 1_000_000) {
            throw new IllegalArgumentException("Request too large. Please use values ≤ 1,000,000.");
        }

        logger.info("Received request for Fibonacci({})", number);
        Instant startTime = Instant.now();

        // Compute Fibonacci number.
        String formattedResult = fibonacciService.getFibonacci(number);

        Instant endTime = Instant.now();
        long durationMs = Duration.between(startTime, endTime).toMillis();
        logger.info("Computed Fibonacci({}) in {} ms (Formatted result length: {})",
                number, durationMs, formattedResult.length());

        return formattedResult;
    }
}
