package com.example.fibonacci_app.service;

import com.example.fibonacci_app.util.FibonacciFormatter;
import com.example.fibonacci_app.util.FibonacciPair;

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

    // Memoization cache for storing Fibonacci pairs (key: index, value:
    // FibonacciPair).
    private final Map<Integer, FibonacciPair> memoizationCache = new ConcurrentHashMap<>();

    /**
     * Computes the Fibonacci number at the specified index n.
     * The method uses caching for intermediate results and formats the final
     * output.
     *
     * @param n The Fibonacci sequence index (0 ≤ n ≤ 1,000,000).
     * @return The formatted Fibonacci number as a String.
     */
    @Cacheable("fibonacciCache") // Caches only the final result.
    public String getFibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number must be non-negative.");
        }
        if (n == 0)
            return "0";
        if (n == 1)
            return "1";

        // 🔹 Start from F(1)
        int start = 1;
        BigInteger prev = BigInteger.ZERO; // F(0)
        BigInteger curr = BigInteger.ONE; // F(1)

        // 🔹 Check cache for the highest available checkpoint <= n
        for (Integer cachedIndex : memoizationCache.keySet()) {
            if (cachedIndex <= n && cachedIndex > start) {
                start = cachedIndex;
                FibonacciPair pair = memoizationCache.get(cachedIndex);
                prev = pair.getPrev();
                curr = pair.getCurr();
            }
        }

        logger.info("Starting Fibonacci computation from cached index: {}", start);

        // 🔹 Compute iteratively from the checkpoint to n
        for (int i = start + 1; i <= n; i++) {
            BigInteger temp = curr.add(prev);
            prev = curr;
            curr = temp;

            // 🔹 Cache intermediate values every 10,000 steps
            if (i % 10_000 == 0) {
                memoizationCache.put(i, new FibonacciPair(prev, curr));
                logger.info("Caching Fibonacci index: F({}) and F({})", i - 1, i);
            }
        }

        return FibonacciFormatter.formatResult(curr.toString());
    }
}
