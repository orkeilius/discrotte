export default function ProfileImage({username}) {
    const color = {
        backgroundColor: `hsl(${getHue(
            username
        )}deg 65% 65%)`,
    };
    return (
        <div className={`rounded-full h-10 aspect-square flex items-center justify-center m-2.5`} style={color}>
            <h1 className="text-center font-bold text-xl" >{username.charAt(0).toUpperCase()}</h1>
        </div>
    );
}
function getHue(str) {
    var total = 0;
    for (let i = 0; i < str.length; i++) {
        total += str.charCodeAt(str);
    }
    return total % 360;
}
