import React from "react";
import FeedMessage from "./FeedMessage";
import Grid2 from "@mui/material/Unstable_Grid2"; // Grid version 2

const Feed = () => {
    const messages = ['Hello there!', 'Hi.', 'Hello again.'];  // TODO: query API for messages & update component

    return (
        <Grid2 container spacing={1}>
            <Grid2 xs style={{ marginTop: "3px", borderRight: "solid" }} />
            <Grid2 xs={8}>
                {messages.map((message) => <FeedMessage message={message.toString()} />)}
            </Grid2>
            <Grid2 xs style={{ marginTop: "3px", borderLeft: "solid" }} />
        </Grid2>
    )
}

export default Feed;