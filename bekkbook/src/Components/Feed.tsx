import React from "react";
import FeedMessage from "./FeedMessage";
import Grid2 from "@mui/material/Unstable_Grid2";
import {useQuery} from "react-query";
import {getStatusFeed} from "../api/API"; // Grid version 2

const Feed = () => {
    // const messages = ['Hello there!', 'Hi.', 'Hello again.'];  // TODO: query API for messages & update component
    const messages = useQuery('statusFeed', () => getStatusFeed())

    if (!messages.isSuccess) {
        return <></>
    }

    return (
        <Grid2 container spacing={1}>
            <Grid2 xs style={{ marginTop: "3px", borderRight: "solid" }} />
            <Grid2 xs={8}>
                {messages.data?.statusFeed.map((message) =>
                    <FeedMessage message={message.toString()} />
                )}
            </Grid2>
            <Grid2 xs style={{ marginTop: "3px", borderLeft: "solid" }} />
        </Grid2>
    )
}

export default Feed;