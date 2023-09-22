import { Formulaire } from "./Formulaire";

export function Sidebar() {
  return (
    <div
      className="d-flex flex-column flex-shrink-0 p-3 text-bg-dark prout"
      
      style={{ width: 280 }}
    >
        <span className="fs-4 text-center">Connexion</span>
      
      <hr />
      <ul className="nav nav-pills flex-column mb-auto">
        <Formulaire />
      </ul>
      <hr />
    </div>
  );
}
