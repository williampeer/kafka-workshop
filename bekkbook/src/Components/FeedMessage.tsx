import React from "react";


type MessageProps = {
    message: String
}

const FeedMessage = ( { message }: MessageProps ) => (
    <div style={{ padding: "0.5rem" }}>
        {message}
    </div>
)

export default FeedMessage;