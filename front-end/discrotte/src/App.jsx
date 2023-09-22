import { useState } from "react";
import "./App.css";
import { Formulaire } from "./components/Formulaire";
import { Sidebar } from "./components/Sidebar";
import { Messagerie } from "./components/Messagerie";
import { Message } from "./components/Message";

function App() {

  return (
    <>
    <div className="main">
      <Sidebar/><Messagerie/>
      </div>
    </>
  );
}

export default App;
