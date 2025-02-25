import { useState } from "react";

/**
 * Custom hook to handle Fibonacci calculations.
 * Manages API calls, loading state, and results.
 */
const useFibonacci = () => {
  const [result, setResult] = useState<number | null>(null);
  const [loading, setLoading] = useState<boolean>(false);

  /**
   * Fetches Fibonacci number from the backend API.
   * @param number The Fibonacci sequence index to calculate.
   */
  const calculateFibonacci = async (number: number) => {
    if (number < 0) return; // Ensure only valid numbers

    setLoading(true); // Show loading state
    setResult(null); // Clear previous result

    try {
      const response = await fetch(`/api/fibonacci/${number}`);
      const data = await response.json();
      setResult(data);
    } catch (error) {
      console.error("Error fetching Fibonacci result:", error);
      setResult(null);
    } finally {
      setLoading(false); // Remove loading state
    }
  };

  return { result, loading, calculateFibonacci };
};

export default useFibonacci;
