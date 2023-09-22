//import "./Messagerie.css";
import { Message } from "./Message";
export function Messagerie() {
  return (
    <div className="flex flex-col min-h-full">
        <Message author="me" text="holla" time="11:00" />
        <Message author="nicolas" text="lorem sdf sf sdfqlmfnmsdf flj djsjmiksjfb sjkxjjfjjddldjj df ffnkllfdfk nndndfdg  jfjsskskfjjfjffj,,nnejjjjnnnsdnsi ismnfk fssjikjikssjkjsdd dlff  s" time="12:00" />

        <input className="bg-[#383A40] w-11/12 rounded-xl mb-2 m-auto p-2 px-4 text-white" type="text" id="envoieMess" name="message" />
    </div>
  );
}
