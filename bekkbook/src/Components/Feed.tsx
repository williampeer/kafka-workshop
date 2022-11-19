import React from "react";
import Grid2 from "@mui/material/Unstable_Grid2";
import {useQuery} from "react-query";
import {getHelloWorldFeed, getStatusFeed} from "../api/API";
import {
    BekkbookMessageRecordList,
    ConsumerRecordWithStringValue,
    ConsumerRecordWithStringValueList
} from "../types/ApiTypes";
import GenericFeedRecord from "./GenericFeedRecord";

const interleaveAndSortFeeds =
    (statusFeed: BekkbookMessageRecordList, helloWorldFeed: ConsumerRecordWithStringValueList): ConsumerRecordWithStringValue[] => {
    return (statusFeed.recordList.map((record) => {
        return {
            topicName: record.topicName,
            partition: record.partition,
            offset: record.offset,
            timestamp: record.timestamp,
            key: record.key,
            value: `StatusMessage: ${record.value.message}`
        }
    }).concat(helloWorldFeed.recordList).sort((a,b) => b.timestamp-a.timestamp))
}

const Feed = () => {
    const statusRecordList = useQuery('statusFeed', () => getStatusFeed(),
        {
            refetchInterval: 3000,
        })
    const helloWorldRecordList = useQuery('helloWorldFeed', () => getHelloWorldFeed(),
        {
            refetchInterval: 3000,
        })

    if (!statusRecordList.isSuccess || !helloWorldRecordList.isSuccess) {
        return <></>
    }

    return (
        <Grid2 container spacing={1}>
            <Grid2 xs style={{ marginTop: "3px", borderRight: "solid" }} />
            <Grid2 xs={8}>
                {interleaveAndSortFeeds(statusRecordList.data, helloWorldRecordList.data)
                // {statusRecordList.data?.recordList.sort((a,b) => b.timestamp-a.timestamp).map((msg) =>
                    .map((record) =>
                    <GenericFeedRecord record={record} key={record.key} />
                )}
            </Grid2>
            <Grid2 xs style={{ marginTop: "3px", borderLeft: "solid" }} />
        </Grid2>
    )
}

export default Feed;