import React, { useState } from "react";
import "./Card.css";
import logo from "../../assets/fibonacci.jpg";
import InputBox from "../InputBox/InputBox";
import SubmitButton from "../SubmitButton/SubmitButton";
import OutputBox from "../OutputBox/OutputBox";
import useFibonacci from "../../hooks/useFibonacci";

/**
 * Main Card component that contains the Fibonacci Calculator UI.
 */
const Card: React.FC = () => {
  const [number, setNumber] = useState<number | "">("");
  const { result, loading, calculateFibonacci } = useFibonacci(); // Use custom hook

  return (
    <div className="card">
      {/* Card Header with Logo */}
      <div className="card-header">
        <a 
          href="https://en.wikipedia.org/wiki/Fibonacci_sequence" 
          target="_blank" 
          rel="noopener noreferrer"
          className="logo-link"
          title="Learn more about Fibonacci Sequence"
        >
          <img src={logo} alt="Fibonacci" className="logo" />
        </a>
      </div>
      
      {/* Title & Description */}
      <h2>Fibonacci Calculator</h2>
      <h3>Calculate Fibonacci numbers dynamically!</h3>

      {/* Input and Button */}
      <div className="input-group">
        <InputBox 
          value={number} 
          onChange={setNumber} 
          onSubmit={() => calculateFibonacci(Number(number))} 
        /> 
        <SubmitButton onClick={() => calculateFibonacci(Number(number))} />
      </div>

      {/* Output Box */}
      <OutputBox result={result} loading={loading} />
    </div>
  );
};

export default Card;
