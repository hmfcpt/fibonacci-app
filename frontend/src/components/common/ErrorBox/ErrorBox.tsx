import React from "react";
import "./ErrorBox.css";

interface ErrorBoxProps {
  message: string;
}

/**
 * ErrorBox Component:
 * - Displays an error message when the input is invalid.
 */
const ErrorBox: React.FC<ErrorBoxProps> = ({ message }) => {
  return (
    <div className="error-container">
      <p className="error-label">⚠️ Error:</p>
      <div className="error-box">
        <span className="error-text">{message}</span>
      </div>
    </div>
  );
};

export default ErrorBox;
