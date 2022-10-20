import React from "react";
import FeedMessage from "./FeedMessage";
import Grid2 from "@mui/material/Unstable_Grid2";
import {useQuery} from "react-query";
import {getStatusFeed} from "../api/API";
import {BekkbookMessageRecordList} from "../types/ApiTypes"; // Grid version 2

const Feed = () => {
    const recordList = useQuery('statusFeed', () => getStatusFeed())

    if (!recordList.isSuccess) {
        return <></>
    }

    return (
        <Grid2 container spacing={1}>
            <Grid2 xs style={{ marginTop: "3px", borderRight: "solid" }} />
            <Grid2 xs={8}>
                {recordList.data?.recordList.map((messageRecord) =>
                    <FeedMessage message={messageRecord.message} key={messageRecord.key} />
                )}
            </Grid2>
            <Grid2 xs style={{ marginTop: "3px", borderLeft: "solid" }} />
        </Grid2>
    )
}

export default Feed;