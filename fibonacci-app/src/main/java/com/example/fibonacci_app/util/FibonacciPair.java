package com.example.fibonacci_app.util;

import java.math.BigInteger;

/**
 * Utility class that holds a pair of Fibonacci values.
 * It stores:
 * - The previous Fibonacci number F(n-1)
 * - The current Fibonacci number F(n)
 */
public class FibonacciPair {
    private final BigInteger prev;
    private final BigInteger curr;

    public FibonacciPair(BigInteger prev, BigInteger curr) {
        this.prev = prev;
        this.curr = curr;
    }

    public BigInteger getPrev() {
        return prev;
    }

    public BigInteger getCurr() {
        return curr;
    }
}