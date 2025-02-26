import React from "react";
import "./InputBox.css";

interface InputBoxProps {
  value: string;
  onChange: (value: string) => void;
  onSubmit: () => void;
  disabled?: boolean;
}

/**
 * InputBox Component:
 * - Accepts user input as a string.
 * - Prevents spaces & trims unnecessary characters.
 * - Calls `onSubmit` when the "Enter" key is pressed.
 */
const InputBox: React.FC<InputBoxProps> = ({
  value,
  onChange,
  onSubmit,
  disabled,
}) => {
  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    // Remove spaces and trim leading zeros
    const sanitizedValue = event.target.value
      .replace(/\s+/g, "")
      .replace(/^0+(?=\d)/, "");
    onChange(sanitizedValue);
  };

  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter") {
      onSubmit();
    }
  };

  return (
    <div className="input-box">
      <input
        type="text"
        placeholder="Enter an integer ≥ 0"
        value={value}
        onChange={handleInputChange}
        onKeyDown={handleKeyDown}
        disabled={disabled} // Disable input field if loading
      />
    </div>
  );
};

export default InputBox;
