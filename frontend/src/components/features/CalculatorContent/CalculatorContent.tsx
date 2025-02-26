import React from "react";
import { InputBox, SubmitButton, OutputBox, ErrorBox } from "../../common";
import useFibonacci from "../../../hooks/useFibonacci";
import "./CalculatorContent.css";

/**
 * Shows the Fibonacci calculator UI: input box, button, and result/error output.
 */
const CalculatorContent: React.FC = () => {
  const [inputValue, setInputValue] = React.useState<string>("");
  const { result, error, loading, calculateFibonacci } = useFibonacci();

  return (
    <div className="calculator-content">
      <h2>Fibonacci Calculator</h2>
      <h3>Calculate Fibonacci numbers dynamically!</h3>

      <div className="input-group">
        <InputBox 
          value={inputValue} 
          onChange={setInputValue} 
          onSubmit={() => !loading && calculateFibonacci(inputValue)} // Prevent input during API call
          disabled={loading} // Disable input when loading
        />
        <SubmitButton 
          onClick={() => !loading && calculateFibonacci(inputValue)} 
          disabled={loading} // Disable button while loading
        />
      </div>

      {error ? <ErrorBox message={error} /> : <OutputBox result={result} loading={loading} />}
    </div>
  );
};

export default CalculatorContent;
