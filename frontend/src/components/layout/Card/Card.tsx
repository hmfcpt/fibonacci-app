import React, { useState } from "react";
import "./Card.css";
import fibonacciImg from "../../../assets/fibonacci.jpg";
import calculatorImg from "../../../assets/calculator.png"; 
import { CalculatorContent, FibonacciExplanation } from "../../features";

/**
 * Main Card component that toggles between the Fibonacci Calculator and Explanation.
 */
const Card: React.FC = () => {
  // Toggle state to switch between Calculator and Explanation.
  const [showCalculator, setShowCalculator] = useState<boolean>(true);

  const handleLogoClick = () => {
    // Toggle between Calculator and Fibonacci Explanation
    setShowCalculator(!showCalculator);
  };

  return (
    <div className="card">
      {/* 🔹 Card Header with Dynamic Logo */}
      <div className="card-header" onClick={handleLogoClick}>
        <img
          src={showCalculator ? fibonacciImg : calculatorImg} // Toggle images
          alt={showCalculator ? "Fibonacci" : "Calculator"}
          className="logo"
          title={showCalculator ? "Click to learn about Fibonacci Sequence" : "Click to go back to Calculator"}
        />
      </div>

      {/* 🔹 Conditionally Render Content */}
      {showCalculator ? <CalculatorContent /> : <FibonacciExplanation />}
    </div>
  );
};

export default Card;
