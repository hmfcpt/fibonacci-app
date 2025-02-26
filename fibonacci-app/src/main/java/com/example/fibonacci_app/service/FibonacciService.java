package com.example.fibonacci_app.service;

import com.example.fibonacci_app.util.FibonacciFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service responsible for efficiently calculating Fibonacci numbers.
 * - Uses an iterative approach (O(n) time, O(1) additional space).
 * - Caches intermediate results every 10,000 iterations.
 * - Always starts computation from the highest cached value to minimize work.
 * - Formats the final result to a maximum of 14 characters using scientific
 * notation.
 */
@Service
public class FibonacciService {

    private static final Logger logger = LoggerFactory.getLogger(FibonacciService.class);

    // In-memory cache for intermediate Fibonacci values (key: index, value:
    // Fibonacci number).
    private final Map<Integer, BigInteger> memoizationCache = new ConcurrentHashMap<>();

    /**
     * Computes the Fibonacci number at the specified index n.
     * The method uses caching for intermediate results and formats the final
     * output.
     *
     * @param n The Fibonacci sequence index (0 ≤ n ≤ 1,000,000).
     * @return The formatted Fibonacci number as a String.
     */
    @Cacheable("fibonacciCache") // Caches the final formatted result for each n.
    public String getFibonacci(int n) {
        // Validate input (should already be checked in the controller, but we validate
        // here too).
        if (n < 0) {
            throw new IllegalArgumentException("Number must be non-negative.");
        }
        if (n == 0)
            return BigInteger.ZERO.toString();
        if (n == 1)
            return BigInteger.ONE.toString();

        // Determine the highest cached index less than or equal to n.
        int start = 2;
        BigInteger prev = BigInteger.ZERO;
        BigInteger curr = BigInteger.ONE;
        for (Integer cachedIndex : memoizationCache.keySet()) {
            if (cachedIndex <= n && cachedIndex > start) {
                start = cachedIndex;
                curr = memoizationCache.get(cachedIndex);
                // If previous cached value is not available, default to zero.
                prev = memoizationCache.getOrDefault(cachedIndex - 1, BigInteger.ZERO);
            }
        }

        // Log the starting index for computation.
        logger.info("Starting Fibonacci computation from cached index: {}", start);

        // Compute Fibonacci iteratively from (start + 1) up to n.
        for (int i = start + 1; i <= n; i++) {
            BigInteger temp = curr.add(prev);
            prev = curr;
            curr = temp;

            // Cache intermediate values every 10,000 steps.
            if (i % 10_000 == 0) {
                memoizationCache.put(i, curr);
                logger.info("Caching intermediate Fibonacci({})", i);
            }
        }

        // Format the computed Fibonacci number so that its string representation does
        // not exceed 14 characters.
        return FibonacciFormatter.formatResult(curr.toString());
    }
}
