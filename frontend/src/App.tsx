import React from "react";
import "./App.css";
import { Background, Card } from "./components/layout";

const App: React.FC = () => {
  return (
    <div className="app-container">
      <Background />
      <Card />
    </div>
  );
};

export default App;
