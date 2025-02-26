import React from "react";
import "./OutputBox.css";

interface OutputBoxProps {
  result: string | null;
  loading: boolean;
}

const OutputBox: React.FC<OutputBoxProps> = ({ result, loading }) => {
  return (
    <div className="output-container">
      <p className="output-label">✅ Result:</p>
      <div className="output-box">
        {loading ? (
          <span className="loading-text">🔄</span>
        ) : result !== null ? (
          <span className="result-text">{result}</span>
        ) : (
          <span className="result-placeholder">--</span>
        )}
      </div>
    </div>
  );
};

export default OutputBox;
