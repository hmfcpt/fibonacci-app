import React from "react";
import "./SubmitButton.css";

interface SubmitButtonProps {
  onClick: () => void;
}

const SubmitButton: React.FC<SubmitButtonProps> = ({ onClick }) => {
  return (
    <button className="submit-button" onClick={onClick}>
      Calculate
    </button>
  );
};

export default SubmitButton;
