import { useState } from "react";
import { Sidebar } from "./components/Sidebar";
import { Messagerie } from "./components/Messagerie";
import { Message } from "./components/Message";

function App() {

  return (
    <>
      <div className="flex min-h-[100vh] bg-[#313338]">
        <Sidebar />
        <Messagerie />
      </div>
    </>
  );
}

export default App;
