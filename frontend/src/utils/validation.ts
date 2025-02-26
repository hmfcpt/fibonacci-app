/**
 * Validates the user input for Fibonacci calculation.
 * @param input The Fibonacci index as a string.
 * @param maxLimit The maximum allowed Fibonacci index.
 * @returns A validation error message or `null` if valid.
 */
export const validateInput = (input: string, maxLimit: number): string | null => {
    // Check if input is a valid non-negative integer
    if (!/^\d+$/.test(input)) {
      return "Invalid input! Please enter a positive integer.";
    }
  
    const number = parseInt(input, 10);
    if (number > maxLimit) {
      return `Maximum allowed input is ${maxLimit}.`;
    }
  
    return null; // Input is valid
  };
  