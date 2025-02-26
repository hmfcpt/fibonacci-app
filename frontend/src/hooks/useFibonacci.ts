import { useState } from "react";
import { validateInput } from "../utils/validation"; // Import the validation function

/**
 * Custom hook to handle Fibonacci calculations.
 * - Manages API calls, validation, loading state, and error handling.
 */
const useFibonacci = () => {
  // 🔹 State to store the result of the Fibonacci calculation.
  const [result, setResult] = useState<string | null>(null);
  // 🔹 State to store any error messages.
  const [error, setError] = useState<string | null>(null);
  // 🔹 State to indicate if the API request is in progress.
  const [loading, setLoading] = useState<boolean>(false);
  // 🔹 Maximum allowed input based on backend constraints.
  const MAX_INDEX = 1_000_000;

  /**
   * Fetches the Fibonacci number from the backend API.
   * @param input The Fibonacci index as a string.
   */
  const calculateFibonacci = async (input: string) => {
    // Validate the input using the utility function
    const validationError = validateInput(input, MAX_INDEX);
    if (validationError) {
      setError(validationError);
      setResult(null);
      return;
    }

    // Reset state before fetching
    setLoading(true);
    setResult(null);
    setError(null);

    try {
      // 🔹 Send request to the API (input is a string, as expected by the API)
      const response = await fetch(`/api/fibonacci/${input}`);
      const data = await response.text();

      // Check for backend errors in response
      if (data.startsWith("Error:")) {
        setError(data);
        setResult(null);
      } else {
        setResult(data); // Store the valid Fibonacci result
      }
    } catch (error) {
      console.error("Error fetching Fibonacci result:", error);
      setError("An error occurred. Please try again.");
      setResult(null);
    } finally {
      setLoading(false); // Remove loading state
    }
  };

  return { result, error, loading, calculateFibonacci };
};

export default useFibonacci;
