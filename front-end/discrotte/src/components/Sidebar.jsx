import { useContext, useState } from "react";
import { AppDataContext } from "../App"


export function Sidebar() {


  const [appData, setAppData] = useContext(AppDataContext)
  const [open, setOpen] = useState(true)


  let login = async (event) => {
    event.preventDefault();

    const response = await fetch(import.meta.env.VITE_BACKEND_URL + "/signIn", {
      method: "POST",
      body: JSON.stringify({
        username: event.target.name.value,
        password: event.target.mdp.value
      }),
      headers: appData.headers
    })

    let responseBody = await response.json()

    setAppData((e) => {
      return {
        ...e,
        "isLogged": true,
        "role": responseBody.role,
        "jwt": responseBody.token,
        "headers": { "Authorization": "Bearer " + responseBody.token, ...e.headers },
      }
    })
    setOpen(false)

  }

  let createUser = async (event) => {
    event.preventDefault();

    const response = await fetch(import.meta.env.VITE_BACKEND_URL + "/createUser", {
      method: "POST",
      body: JSON.stringify({
        newUser: event.target.newName.value,
        newPassword: event.target.newMdp.value
      }),
      headers: appData.headers
    })

    let responseBody = await response.json()
  }

  


  return (
    <>
      <button className="absolute left-1" onClick={() => setOpen(!open)}><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24"><path fill="grey" d="M11.36 2c-.21 0-.49.12-.79.32C10 2.7 8.85 3.9 8.4 5.1c-.34.9-.35 1.72-.21 2.33c-.56.1-.97.28-1.13.35c-.51.22-1.59 1.18-1.69 2.67c-.03.52.04 1.05.2 1.55c-.66.19-1.04.43-1.07.44c-.32.12-.85.49-1 .69c-.35.4-.58.87-.71 1.37c-.29 1.09-.19 2.33.34 3.33c.29.56.69 1.17 1.13 1.6c1.44 1.48 3.92 2.04 5.88 2.36c2.39.4 4.89.26 7.12-.66c3.35-1.39 4.24-3.63 4.38-4.24c.29-1.39-.07-2.7-.22-3.02c-.22-.46-.58-.93-1.17-1.23c-.4-.25-.75-.38-1.01-.44c.26-.95-.11-1.7-.62-2.26c-.77-.82-1.56-.94-1.56-.94c.26-.5.36-1.1.22-1.68c-.16-.71-.55-1.16-1.06-1.46c-.52-.31-1.16-.46-1.82-.58c-.32-.06-1.65-.25-2.2-1.01c-.45-.62-.46-1.74-.58-2.07c-.05-.13-.12-.2-.26-.2M16 9.61c.07 0 .13.01.19.01c1.43.16 2.45 1.54 2.28 3.07c-.17 1.53-1.47 2.65-2.9 2.49c-1.43-.18-2.45-1.53-2.28-3.07c.16-1.45 1.35-2.55 2.71-2.5m-7.38 0c1.33.04 2.44 1.17 2.54 2.6c.12 1.54-.95 2.87-2.38 2.98h-.01c-1.43.11-2.69-1.05-2.81-2.59c-.11-1.54.96-2.87 2.39-2.98c.09-.01.18-.01.27-.01m.02 1.7c-.04 0-.07 0-.11.01c-.56.07-.96.58-.89 1.13a1 1 0 0 0 1.13.87c.56-.07.96-.58.9-1.13a1.01 1.01 0 0 0-1.03-.88m7.3.02c-.52.02-.94.42-.98.95a1 1 0 0 0 .95 1.06a1.008 1.008 0 1 0 .14-2.01h-.11m-7.23 4.82c.29-.01.55.08.79.13c1.18.22 2.2.25 2.69.25c.49 0 1.5-.03 2.67-.25c.41-.08.88-.25 1.25 0c.48.32.13 1.47-.61 2.25a4.53 4.53 0 0 1-3.31 1.38c-1.78 0-2.86-.91-3.31-1.38c-.74-.78-1.09-1.93-.62-2.25c.14-.09.29-.13.45-.13Z" /></svg></button>
      <div id="sidebar" className={"flex-column bg-[#2B2D31] p-1 text-white text-center overflow-x-hidden overflox-y-hidden h-screen transition-all " + (open ? "w-64 " : "w-10 p-0 overflow-hidden ")}  >
        <div className={"transition-all overflow-hidden " + (open ? "max-w-64" : "max-w-0")}>

          <h1 className="text-xl m-1">Discrotte</h1>
          <hr className="border-stone-600 mx-2" />
          {!appData.isLogged && (<>
            <h3 className="text-center w-full mb-2">Connexion</h3>
            <form onSubmit={login} className="flex-column mb-auto text-white text-center">
              <div className="m-4 ">
                <label htmlFor="mail">Nom</label><br />
                <input className="rounded-lg bg-[#383A40] w-full p-1" type="text" name="name" />
              </div>
              <div className="m-4 ">
                <label htmlFor="mdp">Mot de passe</label>
                <input className="rounded-lg bg-[#383A40] w-full p-1" name="mdp" type="password" />
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
          </>)}
          {appData.role == "ADMIN" && (<>
            <h3 className="text-center w-full mb-2">Ajouter un utilisateur</h3>
            <form onSubmit={createUser} className="flex-column mb-auto text-white text-center">
              <div className="m-4 ">
                <label htmlFor="mail">Nom</label><br />
                <input className="rounded-lg bg-[#383A40] w-full p-1" type="text" name="newName" />
              </div>
              <div className="m-4 ">
                <label htmlFor="mdp">Mot de passe</label>
                <input className="rounded-lg bg-[#383A40] w-full p-1" type="Password" name="newMdp" />
              </div>
              <div className="submit">
                <input
                  className="bg-slate-600 rounded-full p-1 w-24 m-2 hover:bg-slate-700 transition-all"
                  type="submit"
                  name="create"
                  value="crée"
                  data-action="submit"
                />
              </div>
            </form>
          </>)}
        </div>
      </div>
    </>
  );
}
