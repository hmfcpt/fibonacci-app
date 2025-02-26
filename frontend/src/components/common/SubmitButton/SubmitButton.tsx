import React from "react";
import "./SubmitButton.css";

interface SubmitButtonProps {
  onClick: () => void;
  disabled?: boolean; 
}

/**
 * SubmitButton Component:
 * - Triggers Fibonacci calculation on click.
 * - Disables the button while an API request is pending.
 */
const SubmitButton: React.FC<SubmitButtonProps> = ({ onClick, disabled }) => {
  return (
    <button className="submit-button" onClick={onClick} disabled={disabled}>
      {disabled ? "Calculating..." : "Calculate"}
    </button>
  );
};

export default SubmitButton;
