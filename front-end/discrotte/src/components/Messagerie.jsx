window.global ||= window;
import { useContext, useEffect, useRef, useState } from "react";
import { AppDataContext } from "../App"
import Discrotte from "../assets/discrotte.gif"
import { Message } from "./Message";


let requestSetting = {
  method: "POST",
  headers: {
    "Authorization": 'Basic ' + btoa("e" + ":" + "e"),
    "mode": "cors",
    //"Access-Control-Allow-Origin": '*',
    "Content-Type": 'text/plain;charset=UTF-8',
    "X-Requested-With": "XMLHttpRequest",
  },
  //referrerPolicy: "no-referrer",
}


export function Messagerie() {

  const [appData, setAppData] = useContext(AppDataContext)
  const [message, setMessage] = useState([])
  const [socket, setSocket] = useState()

  const scrollWarp = useRef(null)

  async function queryMessage() {
    if (message.length == 0) {
      let now = new Date
      var time = now.getTime()
    }
    else {
      var time = message[0].date
    }

    let response = await fetch(import.meta.env.VITE_BACKEND_URL + "/message/getOld", { method: "POST", headers: appData.headers, body: time })

    let requestJSON = await response.json()
    requestJSON = requestJSON.reverse()
    console.log(requestJSON)


    setMessage((e) => { return [...e, ...requestJSON] })
  }

  async function sendMessage(event) {
    if (event.key != 'Enter') {
      return
    }
    socket.send(event.target.value)
    //await fetch(import.meta.env.VITE_BACKEND_URL + "/message/send", {method: "POST", headers: appData.headers, body: event.target.value })
    event.target.value = ""
  }

  function connect() {
    let newSocket = new WebSocket(import.meta.env.VITE_BACKEND_SOCKET + "/chat?jwt=" + appData.jwt)

    newSocket.onmessage = function (event) {
      setMessage((e) => { return [...e, JSON.parse(event.data)] })
      if (scrollWarp.current.scrollTop >= scrollWarp.current.scrollHeight - 1000) {
        setTimeout(() => { scrollWarp.current.scrollTo(0, scrollWarp.current.scrollHeight) }, 0)
      }
    }
    setSocket(newSocket)
  }




  useEffect(() => {
    async function loadMessage() {
      await queryMessage()
      setTimeout(() => { scrollWarp.current.scrollTo(0, scrollWarp.current.scrollHeight) }, 0)
    }

    // close old websocket
    if(socket !== undefined) {
      socket.close()
    }

    if (appData.isLogged) {
      loadMessage()
      connect()
    }
    
  }, [appData.isLogged])



  return (
    <div className="flex flex-col w-full h-[100vh] ">
      <div ref={scrollWarp} className="flex flex-col w-full overflow-scroll flex-auto">
        <img src={Discrotte} className="w-52 mx-auto opacity-20 mb-12" />
        {appData.isLogged ? (
          <div className="mt-auto flex flex-col justify-center ">
            {message.map(elem => {
              return (

                <Message key={elem.date} text={elem.text} author={elem.author} time={elem.date} />
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
