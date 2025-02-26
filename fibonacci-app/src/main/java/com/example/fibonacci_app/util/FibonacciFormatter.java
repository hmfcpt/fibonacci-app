package com.example.fibonacci_app.util;

/**
 * Utility class for formatting Fibonacci numbers.
 * Converts a full numeric string into a compact scientific notation format
 * that does not exceed a maximum display length.
 */
public class FibonacciFormatter {

    // Maximum characters allowed in the formatted result.
    public static final int MAX_DISPLAY_LENGTH = 14;

    /**
     * Formats a large number (represented as a String) into scientific notation.
     * If the input string's length is less than or equal to MAX_DISPLAY_LENGTH, it
     * returns it as is.
     * Otherwise, it returns a compact notation in the format:
     * [mantissa]e+[exponent].
     *
     * For example, if num = "1306994223763..." (with 309 digits),
     * it calculates the exponent as (309 - 1) and builds a mantissa using available
     * characters.
     *
     * @param num The full Fibonacci number as a String.
     * @return A formatted string that does not exceed MAX_DISPLAY_LENGTH
     *         characters.
     */
    public static String formatResult(String num) {
        if (num.length() <= MAX_DISPLAY_LENGTH) {
            return num;
        }
        // The exponent is the number of digits minus one.
        int exponent = num.length() - 1;
        String exponentStr = Integer.toString(exponent);

        // Reserve 2 characters for "e+" and the length of the exponent.
        int availableMantissaChars = MAX_DISPLAY_LENGTH - 2 - exponentStr.length();
        // Ensure at least 2 characters for a valid mantissa (e.g., "1.0")
        if (availableMantissaChars < 2) {
            availableMantissaChars = 2;
        }

        // Build the mantissa: first digit + "." + next (availableMantissaChars - 2)
        // digits.
        String mantissa = num.charAt(0) + "."
                + num.substring(1, Math.min(1 + (availableMantissaChars - 2), num.length()));
        return mantissa + "e+" + exponentStr;
    }
}
