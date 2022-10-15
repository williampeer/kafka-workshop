import React from "react";
import FeedMessage from "./FeedMessage";
import Grid2 from "@mui/material/Unstable_Grid2"; // Grid version 2

const Feed = () => {
    const messages = ['Hello there!', 'Hi.', 'Hello again.'];  // TODO: query API for messages & update component

    return (
        <Grid2 container spacing={1}>
            <Grid2 xs />
            <Grid2 xs={6}>
                {messages.map((message) => <FeedMessage message={message.toString()} />)}
            </Grid2>
            <Grid2 xs />
        </Grid2>
    )
}

export default Feed;