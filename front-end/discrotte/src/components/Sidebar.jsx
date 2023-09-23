import { useContext } from "react";
import {AppDataContext} from "../App"




export function Sidebar() {
  
  const [ appData, setAppData ] = useContext(AppDataContext)
  console.log(appData.isLogged)
  let login = async (event) => {
    event.preventDefault();

    const response = await fetch(import.meta.env.VITE_BACKEND_URL +"/getUser?", {
      method: "POST",
      body: event.target.name.value,
      headers: {
        "Authorization": 'Basic ' + btoa(event.target.name.value + ":" + event.target.mdp.value),
        "mode": "cors",
        "Access-Control-Allow-Origin": '*',
        "Content-Type": 'text/plain;charset=UTF-8',
        "X-Requested-With": "XMLHttpRequest",
      }
    })

    console.log(await response.json())

    setAppData((e) => {return {...e, isLogged : true}})

  }
  


  return (
    <div className={"flex-column bg-[#2B2D31] p-1 text-white text-center overflow-hidden transition-all " + (appData.isLogged? "w-8":"w-64") }  >
      <h1 className="text-xl m-1">Discrotte</h1>
      <hr className="border-stone-600 mx-2" />
      <h3 className="text-center w-full mb-2">Connexion</h3>
      <form onSubmit={login}  className="flex-column mb-auto text-white text-center">
        <div className="m-4 ">
          <label htmlFor="mail">Nom</label><br />
          <input className="rounded-lg bg-[#383A40] p-1" type="text" name="name" />
        </div>
        <div className="m-4 ">
          <label htmlFor="mdp">Mot de passe</label>
          <input className="rounded-lg bg-[#383A40] p-1" name="mdp" type="password" />
        </div>
        <div className="submit">
          <input
            className="bg-slate-600 rounded-full p-1 w-24 m-2 hover:bg-slate-700 transition-all"
            type="submit"
            name="envoye"
            value="log in"
            data-action="submit"
          />
        </div>
      </form>
    </div>
  );
}
