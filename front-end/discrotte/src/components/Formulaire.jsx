import { useState } from "react";

export function Formulaire() {
  return (
    <>
      <div class="formm">
        <form action="Connexion" method="post">
          <div class="mail">
            <label for="mail">E-mail</label>
            <input type="email" id="mail" name="user_mail" />
          </div>
          <div class="mdp">
            <label for="mdp">Mot de passe</label>
            <input id="mdp" name="user_mdp" type="password" />
          </div>
          <div class="submit">
            <input
              class="btEnv"
              type="submit"
              name="envoi"
              value="ENVOYE"
              data-action="submit"
            />
          </div>
        </form>
      </div>
    </>
  );
}
