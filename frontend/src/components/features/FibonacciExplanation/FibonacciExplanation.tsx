import React from "react";
import "./FibonacciExplanation.css";

/**
 * Displays a brief explanation of the Fibonacci sequence.
 */
const FibonacciExplanation: React.FC = () => {
  return (
    <div className="fib-explanation-container">
      <h2>What is the Fibonacci Sequence?</h2>
      <p>
        The Fibonacci sequence is a series of numbers where each number is the sum 
        of the two preceding ones, starting from 0 and 1.
      </p>
      
      {/* Centered Formulas */}
      <p>F(0) = 0, F(1) = 1</p>
      <p>F(n) = F(n-1) + F(n-2)</p>
      
      <p>
        This sequence appears in many areas of mathematics and nature, such as 
        the arrangement of leaves on a stem or the spiral patterns of shells.
      </p>
    </div>
  );
};

export default FibonacciExplanation;
