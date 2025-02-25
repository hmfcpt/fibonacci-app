import React from "react";
import "./App.css";
import Background from "./components/Background/Background";
import Card from "./components/Card/Card";

const App: React.FC = () => {
  return (
    <div className="app-container">
      <Background />
      <Card />
    </div>
  );
};

export default App;
