import "./Messagerie.css"
export function Message(){
    return(
        <div className="message">
          <div className="image">
            <img src="/public/Quentin.png" alt="Avatar" />
          </div>
          <div className="text">
            <p>Lorem ipsum dolor sit amet.
            </p>
          </div>
          <span className="time">11:00</span>
        </div>
    )
}