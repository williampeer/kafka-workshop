import React from "react";
import {BekkbookMessageRecord} from "../types/ApiTypes";


type FeedMessageProps = {
    bekkbookStatusMessage: BekkbookMessageRecord
}

const FeedMessage = ( { bekkbookStatusMessage }: FeedMessageProps ) => (
    <div style={{ padding: "2rem 1rem", borderBottom: "solid", borderColor: "beige" }}>
        {/*Topic: {bekkbookStatusMessage.topicName}<br/>*/}
        partition:{bekkbookStatusMessage.partition}{', '}offset:{bekkbookStatusMessage.partition}
        <br/>
        Key: {bekkbookStatusMessage.key}
        <br/>
        <br/>
        {bekkbookStatusMessage.value.message}
    </div>
)

export default FeedMessage;