import "./messagerie.css";
import { Message } from "./Message";
export function Messagerie() {
  return (
    <div className="rien">
      <>
        <Message/><Message/>

        <div className="envoieMessage">
        <input type="text" id="envoieMess" name="message" />
        </div>
        
      </>
    </div>
  );
}
