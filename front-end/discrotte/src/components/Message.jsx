import ProfileImage from "./ProfileImage.jsx"


export function Message({text,author,time}){
    return(
        <div className="flex text-white">
        <ProfileImage username={author} />
          <div className="text">
          <p>
            <span className="font-semibold">{author}  </span>  
            <span className="time">{time}</span><br/>
            {text}
            </p>
          </div>
        </div>
    )
}

// <div className="w-12 h-12 aspect-square bg-blue-600 rounded-full m-2 " />