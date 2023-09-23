import ProfileImage from "./ProfileImage.jsx"


export function Message({ text, author, time }) {

  let timeFormat = new Date(parseInt(time))
    
    return(
        <div className="flex text-white">
        <ProfileImage username={author} />
          <div className="text">
          <p>
            <span className="font-semibold text-lg">{author}  </span>  
            <span className="font-light text-xs">{timeFormat.toLocaleString("fr-FR")}</span><br/>
            {text}
            </p>
          </div>
        </div>
    )
}

// <div className="w-12 h-12 aspect-square bg-blue-600 rounded-full m-2 " />