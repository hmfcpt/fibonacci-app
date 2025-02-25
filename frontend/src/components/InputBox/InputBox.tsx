import React from "react";
import "./InputBox.css";

interface InputBoxProps {
  value: number | "";
  onChange: (value: number) => void;
  onSubmit: () => void; // New prop for handling "Enter" key press
}

const InputBox: React.FC<InputBoxProps> = ({ value, onChange, onSubmit }) => {
  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter") {
      onSubmit(); // Trigger the calculation when Enter is pressed
    }
  };

  return (
    <div className="input-box">
      <input
        type="number"
        placeholder="Enter an integer ≥ 0"
        value={value}
        min="0"
        onChange={(e) => onChange(Number(e.target.value))}
        onKeyDown={handleKeyDown} // Listen for Enter key press
      />
    </div>
  );
};

export default InputBox;
