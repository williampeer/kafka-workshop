import React from "react";


type MessageProps = {
    message: String
}

const FeedMessage = ( { message }: MessageProps ) => (
    <div style={{ padding: "2rem 1rem", borderBottom: "solid", borderColor: "beige" }}>
        {message}
    </div>
)

export default FeedMessage;