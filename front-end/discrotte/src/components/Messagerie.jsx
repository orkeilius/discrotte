import { useContext, useEffect, useRef, useState } from "react";
import { AppDataContext } from "../App"
import Discrotte from "../assets/discrotte.gif"
import { Message } from "./Message";

let requestSetting = {
  method: "POST",
  headers: {
    "Authorization": 'Basic ' + btoa("e" + ":" + "e"),
    "mode": "cors",
    "Access-Control-Allow-Origin": '*',
    "Content-Type": 'text/plain;charset=UTF-8',
    "X-Requested-With": "XMLHttpRequest",
  },
  //referrerPolicy: "no-referrer",
}


export function Messagerie() {

  const [appData, setAppData] = useContext(AppDataContext)
  const [message, setMessage] = useState([])

  const scrollWarp = useRef(null)

  async function queryMessage(nbMessage) {
    let newMessage = []
    let time
    if (message.length == 0) {
      let now = new Date
      time = now.getTime()
    }
    else {
      time = message[0].date
    }
    for (let index = 0; index < nbMessage; index++) {
      let response = await fetch(import.meta.env.VITE_BACKEND_URL + "/message/getOld", { ...requestSetting, body: time })

      let requestJSON = await response.json()
      newMessage.unshift(...requestJSON.reverse())
      if (requestJSON.length != 10) {
        break
      }

      time = requestJSON[9].date
    }
    setMessage((e) => { return [...e, ...newMessage] })
  }
  async function updateMessage() {
  let time
    if (message.length == 0) {
      console.log("skiped")
      time = 0
      return
    }
    else {
      time = message[message.length - 1].date
    }
    let response = await fetch(import.meta.env.VITE_BACKEND_URL + "/message/getNew", { ...requestSetting, body: time })
    let requestJSON = await response.json()
    setMessage((e) => { return [...e, ...requestJSON] })
    console.log(scrollWarp.current)
    if (scrollWarp.current.scrollTop >= scrollWarp.current.scrollHeight -1000 ) {
        setTimeout(()=>{scrollWarp.current.scrollTo(0, scrollWarp.current.scrollHeight)},0)
      }
  }


  
  async function sendMessage(event) {
    if (event.key != 'Enter') {
      return
    }

    await fetch(import.meta.env.VITE_BACKEND_URL + "/message/send", { ...requestSetting, body: event.target.value })
    event.target.value = ""
  }

  

  useEffect(() => {
    async function loadMessage() {
      await queryMessage(10)
      setTimeout(()=>{scrollWarp.current.scrollTo(0, scrollWarp.current.scrollHeight)},0)
    }

    if (appData.isLogged ) {
      loadMessage()
    }
  }, [appData.isLogged])
  
  useEffect(() => {
    let interval = setInterval(() => { updateMessage() }, 1000 )
    return () => clearInterval(interval);
  }, [message])
   
  return (
    <div className="flex flex-col w-full h-[100vh] ">
      <div ref={scrollWarp} className="flex flex-col w-full overflow-scroll flex-auto">
        <img src={Discrotte} className="w-52 mx-auto opacity-20 mb-12" />
        {appData.isLogged ? (
          <div className="mt-auto flex flex-col justify-center ">
            {message.map(elem => {
              return (

                <Message key={elem.data} text={elem.text} author={elem.author} time={elem.date} />
              )
            })}
          </div>

        ) : (
          <div className="m-auto">

            <p className="text-white text-center text-3xl">connecter vous</p>
          </div>
        )}

      </div>
      <input className="bg-[#383A40] w-11/12 rounded-xl mb-2 mx-auto p-2 px-4 text-white" type="text" id="envoieMess" name="message" onKeyDown={sendMessage} />
    </div>
  );
}
