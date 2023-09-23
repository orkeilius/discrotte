import { createContext, useState } from "react";
import { Sidebar } from "./components/Sidebar";
import { Messagerie } from "./components/Messagerie";


export const AppDataContext = createContext(null)
function App() {


  const [appData, SetAppData] = useState({
    isLogged : false
  }) 

  return (
    <>
      <AppDataContext.Provider value={[ appData, SetAppData ]}>
        <div className="flex min-h-[100vh] bg-[#313338]">
          <Sidebar />
          <Messagerie />
        </div>
      </AppDataContext.Provider>
    </>
  );
}

export default App;
