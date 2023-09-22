

export function Sidebar() {
  return (
    <div className=" flex-column bg-[#2B2D31] w-64 p-1 text-white text-center"  >
      <h1 className="text-xl m-1">Discrotte</h1>
      <hr className="border-stone-600 mx-2" />
      <h3 className="text-center w-full mb-2">Connexion</h3>
      <form action="Connexion" method="post" className="flex-column mb-auto text-white text-center">
        <div className="m-4 ">
          <label htmlFor="mail">E-mail</label><br />
          <input className="rounded-lg bg-[#383A40] p-1" type="email" name="user_mail" />
        </div>
        <div className="m-4 ">
          <label htmlFor="mdp">Mot de passe</label>
          <input className="rounded-lg bg-[#383A40] p-1" name="user_mdp" type="password" />
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
